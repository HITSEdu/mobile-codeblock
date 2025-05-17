package hitsedu.board.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hitsedu.board.ui.utils.UpdateType
import hitsedu.ui_kit.models.ScopeGlobalUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.copyScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class BoardViewModel(

) : ViewModel() {
    //TODO("Update logic for tree, fix bugs")
    //TODO("logic for change variable/array name and change value - use AlertDialog")
    //TODO("create function for loop constructor")
    //TODO("create function for condition constructor")
    private var isCurrentlyDragging by mutableStateOf(false)
    var isBottomSheetVisible by mutableStateOf(false)

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun expandBottomSheet() {
        isBottomSheetVisible = true
    }

    fun hideBottomSheet() {
        isBottomSheetVisible = false
    }

    private var _globalScope = MutableStateFlow(
        ScopeGlobalUIO(
            operationUIOS = emptyList(),
            id = Random.nextLong(1, Long.MAX_VALUE),
            variableUIOS = emptyList(),
            arrayUIOS = emptyList(),
        )
    )
    val globalScope: StateFlow<ScopeUIO> = _globalScope

    fun getRandom(): Long {
        val randomLong = Random.nextLong(1, Long.MAX_VALUE)
        return randomLong
    }

    fun addOperation(parent: ScopeUIO, operation: OperationUIO) {
        hideBottomSheet()
        if (parent is ScopeGlobalUIO) {
            if (operation is OperationArrayUIO)
                parent.copy(arrayUIOS = parent.arrayUIOS + operation)
            else if (operation is OperationVariableUIO)
                parent.copy(operationUIOS = parent.operationUIOS + operation)
        }
        _globalScope.update { root ->
            updateScope(root, parent, operation, UpdateType.ADD) as ScopeGlobalUIO
        }
        Log.e("add", _globalScope.value.toString())
    }

    fun removeOperation(parent: ScopeUIO, operation: OperationUIO) {
        _globalScope.update { root ->
            updateScope(root, parent, operation, UpdateType.DELETE) as ScopeGlobalUIO
        }
        Log.e("remove", _globalScope.value.toString())
    }

    private fun updateScope(
        scope: ScopeUIO,
        parent: ScopeUIO,
        operation: OperationUIO,
        type: UpdateType,
    ): ScopeUIO = if (scope == parent) {
        when (type) {
            UpdateType.ADD -> {
//                Log.e("update", "${scope.operationUIOS}\n${operation}")
                copyScope(scope, scope.operationUIOS + operation)
            }

            UpdateType.DELETE -> {
                copyScope(scope, scope.operationUIOS.filterNot { it == operation })
            }
        }
    } else {
        copyScope(
            scope = scope,
            newOperationUIOS = scope.operationUIOS.map { o ->
                when (o) {
                    is OperationIfUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                    is OperationForUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                    is OperationElseUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                    else -> o
                }
            }
        )
    }
}
package hitsedu.board.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hitsedu.board.ui.utils.UpdateType
import hitsedu.ui_kit.models.ScopeGlobalUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.copyScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class BoardViewModel(

) : ViewModel() {
    /**
     * Это просто ужас,
     * я за 4 часа написал вью модель,
     * там такой говнокод, я бы убил себя
     * если у меня будет время, то я отрефакторю,
     * но пока лишь бы работало, простите,
     * я просто писал первое что пришло в голову
     * **/
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

    fun run() {
        // TODO()
        Log.e("Run", globalScope.value.toString())
    }

    fun getRandom(): Long = Random.nextLong(1, Long.MAX_VALUE)

    fun updateName(parent: ScopeUIO, operation: OperationUIO, newName: String) {
        when (operation) {
            is OperationArrayUIO -> {
                _globalScope.update { root ->
                    updateScope(
                        root,
                        parent,
                        operation.copy(name = newName),
                        UpdateType.REPLACE
                    ) as ScopeGlobalUIO
                }
                updateGlobalScope(operation.copy(name = newName), UpdateType.REPLACE)
            }

            is OperationVariableUIO -> {
                _globalScope.update { root ->
                    updateScope(
                        root,
                        parent,
                        operation.copy(name = newName),
                        UpdateType.REPLACE
                    ) as ScopeGlobalUIO
                }
                updateGlobalScope(operation.copy(name = newName), UpdateType.REPLACE)
            }

            else -> {}
        }
    }

    fun addValue(parent: OperationUIO, value: ValueUIO) {
        val updatedOperation = when (parent) {
            is OperationVariableUIO -> parent.copy(value = value.copy(id = getRandom()))
            is OperationArrayUIO -> parent.copy(values = parent.values + value.copy(id = getRandom()))
            is OperationIfUIO -> parent.copy(value = value.copy(id = getRandom()))
            is OperationForUIO -> parent.copy(
                variable = value.copy(id = getRandom()),
                condition = value.copy(id = getRandom()),
                value = value.copy(id = getRandom()),
            )

            is OperationOutputUIO -> parent.copy(value = value.copy(id = getRandom()))
            else -> {
                return
            }
        }
        updateOperationValue(parent, value, UpdateType.ADD)
        _globalScope.value = _globalScope.value.copy(
            variableUIOS = _globalScope.value.variableUIOS.map {
                if (it == parent) updatedOperation as OperationVariableUIO else it
            },
            arrayUIOS = _globalScope.value.arrayUIOS.map {
                if (it == parent) updatedOperation as OperationArrayUIO else it
            }
        )
    }

    fun removeValue(parent: OperationUIO, value: ValueUIO) {
        val updatedOperation = when (parent) {
            is OperationVariableUIO -> parent.copy(value = ValueUIO())
            is OperationArrayUIO -> parent.copy(values = parent.values.filterNot { it == value })
            is OperationIfUIO -> parent.copy(value = ValueUIO())
            is OperationForUIO -> parent.copy(
                variable = ValueUIO(),
                condition = ValueUIO(),
                value = ValueUIO(),
            )

            is OperationOutputUIO -> parent.copy(value = ValueUIO())
            else -> {
                return
            }
        }
        updateOperationValue(parent, value, type = UpdateType.DELETE)
        _globalScope.value = _globalScope.value.copy(
            variableUIOS = _globalScope.value.variableUIOS.map {
                if (it == parent) updatedOperation as OperationVariableUIO else it
            },
            arrayUIOS = _globalScope.value.arrayUIOS.map {
                if (it == parent) updatedOperation as OperationArrayUIO else it
            }
        )
    }

    fun updateValue(parent: OperationUIO, value: ValueUIO, newValue: String) {
        updateValueInGlobalScope(
            parent,
            value,
            ValueUIO(
                id = getRandom(),
                value = newValue,
            ),
        )
    }

    fun addOperation(parent: ScopeUIO, operation: OperationUIO) {
        hideBottomSheet()
        _globalScope.update { root ->
            updateScope(root, parent, operation, UpdateType.ADD) as ScopeGlobalUIO
        }
        updateGlobalScope(operation, UpdateType.ADD)
    }

    fun removeOperation(parent: ScopeUIO, operation: OperationUIO) {
        _globalScope.update { root ->
            updateScope(root, parent, operation, UpdateType.DELETE) as ScopeGlobalUIO
        }
        updateGlobalScope(operation, UpdateType.DELETE)
    }

    private fun updateScope(
        scope: ScopeUIO,
        parent: ScopeUIO,
        operation: OperationUIO,
        type: UpdateType,
    ): ScopeUIO = if (scope == parent) {
        when (type) {
            UpdateType.ADD -> copyScope(scope, scope.operationUIOS + operation)
            UpdateType.DELETE -> copyScope(scope, scope.operationUIOS.filterNot { it == operation })
            UpdateType.REPLACE -> copyScope(scope, scope.operationUIOS.map {
                if (it.id == operation.id) operation else it
            })
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

    private fun updateGlobalScope(operation: OperationUIO, type: UpdateType) {
        when (operation) {
            is OperationArrayUIO -> {
                when (type) {
                    UpdateType.ADD -> _globalScope.value = _globalScope.value.copy(
                        arrayUIOS = _globalScope.value.arrayUIOS + operation
                    )

                    UpdateType.DELETE -> _globalScope.value = _globalScope.value.copy(
                        arrayUIOS = _globalScope.value.arrayUIOS.filterNot { it == operation }
                    )

                    UpdateType.REPLACE -> _globalScope.value = _globalScope.value.copy(
                        arrayUIOS = _globalScope.value.arrayUIOS.map {
                            if (it.id == operation.id) operation else it
                        }
                    )
                }
            }

            is OperationVariableUIO -> {
                when (type) {
                    UpdateType.ADD -> _globalScope.value = _globalScope.value.copy(
                        variableUIOS = _globalScope.value.variableUIOS + operation
                    )

                    UpdateType.DELETE -> _globalScope.value = _globalScope.value.copy(
                        variableUIOS = _globalScope.value.variableUIOS.filterNot { it == operation }
                    )

                    UpdateType.REPLACE -> _globalScope.value = _globalScope.value.copy(
                        variableUIOS = _globalScope.value.variableUIOS.map {
                            if (it.id == operation.id) operation else it
                        }
                    )
                }
            }

            else -> {}
        }
    }


    private fun updateOperationValue(
        parent: OperationUIO,
        newValue: ValueUIO,
        type: UpdateType,
    ) {
        val updatedGlobalScope = updateScopeWithOperation(
            scope = _globalScope.value,
            parent = parent,
            newValue = newValue,
            type = type,
        )

        _globalScope.value = updatedGlobalScope as ScopeGlobalUIO
    }

    private fun updateScopeWithOperation(
        scope: ScopeUIO,
        parent: OperationUIO,
        newValue: ValueUIO,
        type: UpdateType,
    ): ScopeUIO {
        val updatedOperations = scope.operationUIOS.map { op ->
            if (op == parent) updateOperationWithValue(op, newValue, type)
            else updateNestedOperation(op, parent, newValue, type)
        }

        return when (scope) {
            is ScopeGlobalUIO -> scope.copy(operationUIOS = updatedOperations)
            else -> copyScope(scope, updatedOperations)
        }
    }

    private fun updateOperationWithValue(
        op: OperationUIO,
        value: ValueUIO,
        type: UpdateType,
    ): OperationUIO {
        val newId = getRandom()
        return when (op) {
            is OperationVariableUIO ->
                when (type) {
                    UpdateType.ADD -> op.copy(value = value.copy(id = newId))
                    UpdateType.DELETE -> op.copy(value = ValueUIO())
                    UpdateType.REPLACE -> op
                }

            is OperationArrayUIO ->
                when (type) {
                    UpdateType.ADD -> op.copy(values = op.values + value.copy(id = newId))
                    UpdateType.DELETE -> op.copy(values = op.values.filterNot { it == value })
                    UpdateType.REPLACE -> op
                }

            is OperationIfUIO ->
                when (type) {
                    UpdateType.ADD -> op.copy(value = value.copy(id = newId))
                    UpdateType.DELETE -> op.copy(value = ValueUIO())
                    UpdateType.REPLACE -> op
                }

            is OperationForUIO ->
                when (type) {
                    UpdateType.ADD -> op.copy(
                        variable = value.copy(id = getRandom()),
                        condition = value.copy(id = getRandom()),
                        value = value.copy(id = getRandom()),
                    )

                    UpdateType.DELETE -> op.copy(
                        variable = ValueUIO(),
                        condition = ValueUIO(),
                        value = ValueUIO(),
                    )

                    UpdateType.REPLACE -> op
                }

            is OperationOutputUIO ->
                when (type) {
                    UpdateType.ADD -> op.copy(value = value.copy(id = newId))
                    UpdateType.DELETE -> op.copy(value = ValueUIO())
                    UpdateType.REPLACE -> op
                }

            else -> op
        }
    }

    private fun updateNestedOperation(
        op: OperationUIO,
        parent: OperationUIO,
        newValue: ValueUIO,
        type: UpdateType,
    ): OperationUIO = when (op) {
        is OperationIfUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
        is OperationForUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
        is OperationElseUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
        else -> op
    }

    private fun updateValueInGlobalScope(
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ) {
        val updated = updateScopeValues(
            scope = _globalScope.value,
            parent = parent,
            oldValue = oldValue,
            newValue = newValue
        )
        _globalScope.value = updated as ScopeGlobalUIO
    }

    private fun updateScopeValues(
        scope: ScopeUIO,
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): ScopeUIO {
        val updatedOps = scope.operationUIOS.map { op ->
            if (op == parent) replaceValueInOperation(op, oldValue, newValue)
            else recurseNested(op, parent, oldValue, newValue)
        }
        return when (scope) {
            is ScopeGlobalUIO -> scope.copy(operationUIOS = updatedOps)
            else -> copyScope(scope, updatedOps)
        }
    }

    private fun replaceValueInOperation(
        op: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): OperationUIO {
        val newId = getRandom()
        return when (op) {
            is OperationVariableUIO -> op.copy(value = newValue.copy(id = newId))
            is OperationArrayUIO ->
                op.copy(
                    values = op.values.map { v ->
                        if (v.id == oldValue.id) newValue.copy(id = newId) else v
                    }
                )

            is OperationIfUIO -> op.copy(value = newValue.copy(id = newId))
            is OperationForUIO ->
                op.copy(
                    variable = if (op.variable.id == oldValue.id) newValue.copy(id = getRandom()) else op.variable,
                    condition = if (op.condition.id == oldValue.id) newValue else op.condition,
                    value = if (op.value.id == oldValue.id) newValue else op.value,
                )

            is OperationOutputUIO -> op.copy(value = newValue.copy(id = newId))
            else -> op
        }
    }

    private fun recurseNested(
        op: OperationUIO,
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): OperationUIO {
        return when (op) {
            is OperationIfUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
            is OperationForUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
            is OperationElseUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
            else -> op
        }
    }
}
package hitsedu.board.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hitsedu.board.ui.utils.Default
import hitsedu.board.ui.utils.UpdateType
import hitsedu.data.ProjectRepository
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.copyScope
import hitsedu.ui_kit.utils.toProject
import hitsedu.ui_kit.utils.toProjectUIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class BoardViewModel(
    private val repository: ProjectRepository,
) : ViewModel() {
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

    private val _project = MutableStateFlow<ProjectUIO?>(null)
    val project: StateFlow<ProjectUIO?> = _project

    private val _projectCaption = MutableStateFlow(_project.value?.caption ?: "Project №")
    val projectCaption: StateFlow<String> = _projectCaption

    fun setProjectCaption(newCaption: String) {
        _projectCaption.value = newCaption
        _project.value = _project.value?.copy(caption = newCaption)
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
    }

    fun init(id: Long) {
        viewModelScope.launch {
            initProject(id)
        }
    }

    fun run() {
        // TODO()
        Log.e("Run", project.value!!.globalScope.operationUIOS.toString())
    }

    fun getRandom(): Long = Random.nextLong(1, Long.MAX_VALUE)

    fun updateName(parent: ScopeUIO, operation: OperationUIO, newName: String) {
        when (operation) {
            is OperationArrayUIO -> {
                _project.update { root ->
                    updateProjectWithScope(root, parent, operation.copy(name = newName), UpdateType.REPLACE)
                }
            }

            is OperationVariableUIO -> {
                _project.update { root ->
                    updateProjectWithScope(root, parent, operation.copy(name = newName), UpdateType.REPLACE)
                }
            }

            is OperationArrayIndexUIO -> {
                _project.update { root ->
                    updateProjectWithScope(root, parent, operation.copy(name = newName), UpdateType.REPLACE)
                }
            }

            else -> {}
        }
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
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
            is OperationArrayIndexUIO -> parent.copy(
                index = value.copy(id = getRandom()),
                value = value.copy(id = getRandom())
            )

            else -> {
                return
            }
        }
        updateOperationValue(updatedOperation, value, UpdateType.ADD)
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
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
            is OperationArrayIndexUIO -> parent.copy(
                index = ValueUIO(),
                value = ValueUIO(),
            )

            else -> {
                return
            }
        }
        updateOperationValue(updatedOperation, value, type = UpdateType.DELETE)
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
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
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
    }

    fun addOperation(parent: ScopeUIO, operation: OperationUIO) {
        hideBottomSheet()
        _project.update { root ->
            updateProjectWithScope(root, parent, operation, UpdateType.ADD)
        }
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
    }

    fun removeOperation(parent: ScopeUIO, operation: OperationUIO) {
        _project.update { root ->
            updateProjectWithScope(root, parent, operation, UpdateType.DELETE)
        }
        viewModelScope.launch {
            repository.update(_project.value!!.toProject())
        }
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

    private fun updateOperationValue(
        parent: OperationUIO,
        newValue: ValueUIO,
        type: UpdateType,
    ) {
        val updatedGlobalScope = updateScopeWithOperation(
            scope = _project.value?.globalScope ?: Default.project.globalScope,
            parent = parent,
            newValue = newValue,
            type = type,
        )

        _project.update {
            it?.copy(globalScope = updatedGlobalScope)
        }
    }


    private fun updateOperationWithValue(
        op: OperationUIO,
        value: ValueUIO,
        type: UpdateType,
    ): OperationUIO = when (op) {
        is OperationVariableUIO ->
            when (type) {
                UpdateType.ADD -> op.copy(value = value.copy(id = getRandom()))
                UpdateType.DELETE -> op.copy(value = ValueUIO())
                UpdateType.REPLACE -> op
            }

        is OperationArrayUIO ->
            when (type) {
                UpdateType.ADD -> op.copy(values = op.values + value.copy(id = getRandom()))
                UpdateType.DELETE -> op.copy(values = op.values.filterNot { it == value })
                UpdateType.REPLACE -> op
            }

        is OperationIfUIO ->
            when (type) {
                UpdateType.ADD -> op.copy(value = value.copy(id = getRandom()))
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
                UpdateType.ADD -> op.copy(value = value.copy(id = getRandom()))
                UpdateType.DELETE -> op.copy(value = ValueUIO())
                UpdateType.REPLACE -> op
            }

        is OperationArrayIndexUIO ->
            when (type) {
                UpdateType.ADD -> op.copy(
                    index = value.copy(id = getRandom()),
                    value = value.copy(id = getRandom())
                )

                UpdateType.DELETE -> op.copy(
                    index = ValueUIO(),
                    value = ValueUIO(),
                )

                UpdateType.REPLACE -> op
            }

        else -> op
    }

    private fun updateValueInGlobalScope(
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ) {
        val updated = updateScopeValues(
            scope = _project.value!!.globalScope,
            parent = parent,
            oldValue = oldValue,
            newValue = newValue
        )
        _project.update { it?.copy(globalScope = updated) }
    }


    private fun replaceValueInOperation(
        op: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): OperationUIO = when (op) {
        is OperationVariableUIO -> op.copy(value = newValue.copy(id = getRandom()))
        is OperationArrayUIO ->
            op.copy(
                values = op.values.map { v ->
                    if (v.id == oldValue.id) newValue.copy(id = getRandom()) else v
                }
            )

        is OperationIfUIO -> op.copy(value = newValue.copy(id = getRandom()))
        is OperationForUIO ->
            op.copy(
                variable = if (op.variable.id == oldValue.id) newValue.copy(id = getRandom()) else op.variable,
                condition = if (op.condition.id == oldValue.id) newValue.copy(id = getRandom()) else op.condition,
                value = if (op.value.id == oldValue.id) newValue.copy(id = getRandom()) else op.value,
            )

        is OperationOutputUIO -> op.copy(value = newValue.copy(id = getRandom()))
        is OperationArrayIndexUIO -> op.copy(
            index = if (op.index.id == oldValue.id) newValue.copy(id = getRandom()) else op.index,
            value = if (op.value.id == oldValue.id) newValue else op.value,
        )

        else -> op
    }

    private fun updateScopeValues(
        scope: ScopeUIO,
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): ScopeUIO = copyScope(scope, scope.operationUIOS.map { op ->
        if (op.id == parent.id) replaceValueInOperation(op, oldValue, newValue)
        else recurseNested(op, parent, oldValue, newValue)
    })

    private fun recurseNested(
        op: OperationUIO,
        parent: OperationUIO,
        oldValue: ValueUIO,
        newValue: ValueUIO,
    ): OperationUIO = when (op) {
        is OperationIfUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
        is OperationForUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
        is OperationElseUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
        else -> op
    }

    private fun updateScopeWithOperation(
        scope: ScopeUIO,
        parent: OperationUIO,
        newValue: ValueUIO,
        type: UpdateType,
    ): ScopeUIO = copyScope(scope, scope.operationUIOS.map { op ->
        if (op.id == parent.id) updateOperationWithValue(op, newValue, type)
        else updateNestedOperation(op, parent, newValue, type)
    })

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

    private fun updateProjectWithScope(
        project: ProjectUIO?,
        parent: ScopeUIO,
        operation: OperationUIO,
        type: UpdateType,
    ): ProjectUIO? {
        if (project == null) return null
        val updatedGlobalScope = updateScope(project.globalScope, parent, operation, type)
        val updatedScopeUIOS = project.scopeUIOS.map { scope ->
            if (scope == parent) {
                updateScope(scope, parent, operation, type)
            } else scope
        }
        return project.copy(
            globalScope = updatedGlobalScope,
            scopeUIOS = updatedScopeUIOS
        )
    }

    private suspend fun initProject(id: Long): ProjectUIO {
        val p = repository.get(id)?.toProjectUIO() ?: Default.project
        _project.value = p
        _projectCaption.value = _project.value!!.caption
        return p
    }
}
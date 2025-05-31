package hitsedu.board

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hitsedu.board.utils.Default
import hitsedu.board.utils.UpdateType
import hitsedu.board.viewModel.clearErrorsInScope
import hitsedu.board.viewModel.updateErrorsInScope
import hitsedu.board.viewModel.updateProjectWithScope
import hitsedu.board.viewModel.updateScopeValues
import hitsedu.board.viewModel.updateScopeWithOperation
import hitsedu.data.ProjectRepository
import hitsedu.interpreter.InterpreterImpl
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.exception.ConsoleOutputUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.TemplateBoards
import hitsedu.ui_kit.utils.mapper.toConsoleOutputUIO
import hitsedu.ui_kit.utils.mapper.toProjectDBO
import hitsedu.ui_kit.utils.mapper.toProjectUIO
import hitsedu.ui_kit.utils.mapper.toScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val project: StateFlow<ProjectUIO?> = _project.asStateFlow()

    private val _consoleOut = MutableStateFlow<List<ConsoleOutputUIO>>(emptyList())
    val consoleOutput = _consoleOut.asStateFlow()

    fun setProjectCaption(newCaption: String) {
        _project.value = _project.value?.copy(caption = newCaption)
        viewModelScope.launch {
            repository.update(_project.value!!.toProjectDBO())
        }
    }

    fun init(id: Long) {
        when (id) {
            0L -> _project.value = TemplateBoards.templates[0]
            1L -> _project.value = TemplateBoards.templates[1]
            2L -> _project.value = TemplateBoards.templates[2]
            else -> viewModelScope.launch {
                _project.value = repository.get(id)?.toProjectUIO() ?: Default.project
            }
        }
    }

    fun run() {
        viewModelScope.launch(Dispatchers.IO) {
            val interpreter = InterpreterImpl()
            _consoleOut.value = emptyList()
            val scope = project.value?.globalScope?.toScope()
            if (scope != null) {
                interpreter.process(scope)
                val out = interpreter.getConsole()
                val outUIO = out.map {
                    if (it.exception != null) {
                        showError(listOf(it.toConsoleOutputUIO()))
                    }
                    it.toConsoleOutputUIO()
                }
                _consoleOut.value = outUIO
            }
        }
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
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
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
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
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
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
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
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
    }

    fun addOperation(parent: ScopeUIO, operation: OperationUIO) {
        hideBottomSheet()
        _project.update { root ->
            updateProjectWithScope(root, parent, operation, UpdateType.ADD)
        }
        viewModelScope.launch {
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
    }

    fun removeOperation(parent: ScopeUIO, operation: OperationUIO) {
        _project.update { root ->
            updateProjectWithScope(root, parent, operation, UpdateType.DELETE)
        }
        viewModelScope.launch {
            repository.update(_project.value!!.toProjectDBO())
        }
        clearErrors()
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

    private fun showError(consoleOutput: List<ConsoleOutputUIO>) {
        _project.update { project ->
            project?.copy(
                globalScope = updateErrorsInScope(project.globalScope, consoleOutput)
            )
        }
    }

    private fun clearErrors() {
        _project.update { project ->
            project?.copy(
                globalScope = clearErrorsInScope(project.globalScope)
            )
        }
    }
}
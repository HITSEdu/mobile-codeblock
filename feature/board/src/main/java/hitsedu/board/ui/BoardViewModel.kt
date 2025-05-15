package hitsedu.board.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hitsedu.ui_kit.models.data.DataType
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationUIOConditionIf
import hitsedu.ui_kit.models.operation.OperationUIOValue
import hitsedu.ui_kit.models.operation.OperationUIOVariable
import hitsedu.ui_kit.models.scope.ScopeGlobalUIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel(

) : ViewModel() {
    val globalScope = ScopeGlobalUIO(
        operationUIOS = emptyList(),
        functions = listOf(),
    )

    fun runMain() {

    }

    fun updateMainScope() {

    }

    // DND
    private var isCurrentlyDragging by mutableStateOf(false)

    private val _items = MutableStateFlow<List<OperationUIO>>(emptyList())
    val items: StateFlow<List<OperationUIO>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            OperationUIOVariable(
                name = "a",
                type = DataType.Integer,
                value = OperationUIOValue("15"),
            ),
            OperationUIOVariable(
                name = "b",
                type = DataType.Boolean,
                value = OperationUIOValue("true"),
            ),
        )
    }

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addVariable(o: OperationUIO) {
        _items.value += o
    }

    fun removeVariable(o: OperationUIO) {
        _items.value = _items.value.filterNot { it == o }
    }

    fun addValue(value: OperationUIOValue, parent: OperationUIO) {
        _items.value = _items.value.map { item ->
            when {
                item == parent && item is OperationUIOVariable -> item.copy(value = value)
                item == parent && item is OperationUIOConditionIf -> item.copy(value = value)
                else -> item
            }
        }
    }

    fun removeValue(o: OperationUIOValue) {
        _items.value = _items.value.map { item ->
            if (item is OperationUIOVariable && item.value == o) {
                item.copy(value = OperationUIOValue(""))
            } else item
        }
    }

    fun changeVariableName(o: OperationUIOVariable, name: String) {
        val currentList = _items.value.toMutableList()
        val index = currentList.indexOf(o)
        if (index != -1) {
            currentList[index] = o.copy(name = name)
            _items.value = currentList
        }
    }

    fun changeValue(o: OperationUIOValue, value: String) {
        _items.value = _items.value.map { item ->
            if (item is OperationUIOVariable && item.value == o) {
                item.copy(value = OperationUIOValue(value))
            } else item
        }
    }
}
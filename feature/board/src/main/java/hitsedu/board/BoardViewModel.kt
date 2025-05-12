package hitsedu.board

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hitsedu.ui_kit.models.data.DataType
import hitsedu.ui_kit.models.operation.Operation
import hitsedu.ui_kit.models.operation.OperationConditionIf
import hitsedu.ui_kit.models.operation.OperationValue
import hitsedu.ui_kit.models.operation.OperationVariable
import hitsedu.ui_kit.models.scope.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel(

) : ViewModel() {
    val globalScope = GlobalScope(
        operations = emptyList(),
        functions = listOf(),
    )

    fun runMain() {

    }

    fun updateMainScope() {

    }

    // DND
    private var isCurrentlyDragging by mutableStateOf(false)

    private val _items = MutableStateFlow<List<Operation>>(emptyList())
    val items: StateFlow<List<Operation>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            OperationVariable(
                name = "a",
                type = DataType.Integer,
                value = OperationValue("15"),
            ),
            OperationVariable(
                name = "b",
                type = DataType.Boolean,
                value = OperationValue("true"),
            ),
        )
    }

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addVariable(o: Operation) {
        _items.value += o
    }

    fun removeVariable(o: Operation) {
        _items.value = _items.value.filterNot { it == o }
    }

    fun addValue(value: OperationValue, parent: Operation) {
        _items.value = _items.value.map { item ->
            when {
                item == parent && item is OperationVariable -> item.copy(value = value)
                item == parent && item is OperationConditionIf -> item.copy(value = value)
                else -> item
            }
        }
    }

    fun removeValue(o: OperationValue) {
        _items.value = _items.value.map { item ->
            if (item is OperationVariable && item.value == o) {
                item.copy(value = OperationValue(""))
            } else item
        }
    }

    fun changeVariableName(o: OperationVariable, name: String) {
        val currentList = _items.value.toMutableList()
        val index = currentList.indexOf(o)
        if (index != -1) {
            currentList[index] = o.copy(name = name)
            _items.value = currentList
        }
    }

    fun changeValue(o: OperationValue, value: String) {
        _items.value = _items.value.map { item ->
            if (item is OperationVariable && item.value == o) {
                item.copy(value = OperationValue(value))
            } else item
        }
    }
}
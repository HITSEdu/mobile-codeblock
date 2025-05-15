package hitsedu.board.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel(

) : ViewModel() {
    private var isCurrentlyDragging by mutableStateOf(false)

    private val _items = MutableStateFlow<List<OperationUIO>>(emptyList())
    val items: StateFlow<List<OperationUIO>> = _items.asStateFlow()

    init {
        _items.value = listOf(
            OperationVariableUIO(
                name = "a",
                value = ValueUIO(),
            ),
            OperationVariableUIO(
                name = "b",
                value = ValueUIO(),
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

    fun addValue(value: ValueUIO, parent: OperationUIO) {
        //TODO("Change for nested structures")
        _items.value = _items.value.map { item ->
            when {
                item == parent && item is OperationVariableUIO -> item.copy(value = value)
                item == parent && item is OperationIfUIO -> item.copy(value = value)
                else -> item
            }
        }
    }

    fun removeValue(o: ValueUIO) {
        //TODO("Change for nested structures")
        _items.value = _items.value.map { item ->
            if (item is OperationVariableUIO && item.value == o) {
                item.copy(value = ValueUIO())
            } else item
        }
    }

    fun changeVariableName(o: OperationVariableUIO, name: String) {
        //TODO("Change for nested structures")
        val currentList = _items.value.toMutableList()
        val index = currentList.indexOf(o)
        if (index != -1) {
            currentList[index] = o.copy(name = name)
            _items.value = currentList
        }
    }

    fun changeValue(value: ValueUIO, newValue: String) {
        //TODO("Change for nested structures")
        _items.value = _items.value.map { item ->
            if (item is OperationVariableUIO && item.value == value) {
                item.copy(value = ValueUIO(newValue))
            } else item
        }
    }
}
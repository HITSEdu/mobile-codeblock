package hitsedu.interpreter.processor

import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationVariable

fun OperationArray.process(variables: List<OperationVariable>): OperationArray {
    val values = values.map { value ->
        val variable = variables.find { it.name == value.value }
        value.copy(value = variable?.value?.value ?: value.value)
    }
    return copy(values = values)
}
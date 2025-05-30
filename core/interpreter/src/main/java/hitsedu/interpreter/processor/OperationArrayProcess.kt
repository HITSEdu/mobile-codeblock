package hitsedu.interpreter.processor

import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationVariable

fun OperationArray.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): E? {
    val values = values.map { value ->
        val variable = variables.find { it.name == value.value }
        value.copy(value = variable?.value?.value ?: value.value)
    }

    arrays.add(copy(values = values))
    return null
}
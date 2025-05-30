package hitsedu.interpreter.processor

import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.utils.Type

fun OperationArray.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): E? {
    val processedValues = values.map { value ->
        variables.find { it.name == value.value }?.value?.let { variableValue ->
            value.copy(value = variableValue.value, type = variableValue.type)
        } ?: run {
            when {
                value.value.toIntOrNull() != null -> value.copy(type = Type.INT)
                value.value.toDoubleOrNull() != null -> value.copy(type = Type.DOUBLE)
                value.value == "true" || value.value == "false" -> value.copy(type = Type.BOOLEAN)
                else -> value
            }
        }
    }

    arrays.add(copy(values = processedValues))
    return null
}
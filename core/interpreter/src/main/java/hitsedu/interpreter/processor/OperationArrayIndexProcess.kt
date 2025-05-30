package hitsedu.interpreter.processor

import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationArrayIndex
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserMath

fun OperationArrayIndex.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): E? {
    val idx = index.value.toIntOrNull() ?: ParserMath.parseMathExpression(index.value) { name ->
        Value(name).process(
            variables = variables,
            arrays = arrays
        )
    }

    val calculatedValue = ParserMath.parseMathExpression(value.value) { name ->
        Value(name).process(variables, arrays)
    }

    val array = arrays.find { it.name == name } ?: return E("Array $name not found", id)
    if (idx !in array.values.indices) return E("Index $idx out of bounds", id)

    val newValues = array.values.toMutableList().apply {
        this[idx] = Value(calculatedValue.toString())
    }
    arrays[arrays.indexOf(array)] = array.copy(values = newValues)
    return null
}
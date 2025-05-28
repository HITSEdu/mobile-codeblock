package hitsedu.interpreter.processor

import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationIf
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.Parser

fun OperationIf.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): Boolean {
    fun resolve(name: String): Int {
        return variables.find { it.name == name }?.value?.value?.toIntOrNull()
            ?: arrays.firstOrNull { array ->
                name.startsWith(array.name + "[") && name.endsWith("]")
            }?.let { array ->
                val indexStr = name.substring(array.name.length + 1, name.length - 1)
                val index = indexStr.toIntOrNull() ?: return@let null
                array.values.getOrNull(index)?.value?.toIntOrNull()
            } ?: error("Cannot resolve value: $name")
    }

    return Parser.parseLogicExpression(this.value.value, ::resolve)
}
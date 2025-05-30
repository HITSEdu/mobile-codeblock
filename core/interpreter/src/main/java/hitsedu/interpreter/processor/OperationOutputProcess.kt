package hitsedu.interpreter.processor

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserLogic
import hitsedu.interpreter.syntax.ParserMath
import hitsedu.interpreter.utils.Operators

fun OperationOutput.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): ConsoleOutput {
    if (value.value.startsWith("\"") && value.value.endsWith("\""))
        return ConsoleOutput(value.value)

    for (operator in Operators.LOGIC) {
        if (value.value.contains(operator.key)) {
            return try {
                val logic = ParserLogic.parseLogicExpression(
                    value.value,
                    resolve = { name ->
                        variables.find { it.name == name }?.value?.value?.toIntOrNull()
                            ?: arrays.firstOrNull { array ->
                                name.startsWith(array.name + "[") && name.endsWith("]")
                            }?.let { array ->
                                val indexStr = name.substring(array.name.length + 1, name.length - 1)
                                val index = indexStr.toIntOrNull() ?: return@let null
                                array.values.getOrNull(index)?.value?.toIntOrNull()
                            } ?: error("Cannot resolve value: $name")
                    }
                )
                ConsoleOutput("$logic")
            } catch (e: Exception) {
                ConsoleOutput("", E("${e.message}", id))
            }
        }
    }

    variables.forEach {
        if (value.value == it.name)
            return ConsoleOutput(it.value.value)
    }

    arrays.forEach {
        val parts = value.value.split("[")
        if (parts[0] == it.name) {
            if (parts.size == 1) {
                val arrStr = it.values.joinToString(", ") { i -> i.value }
                return ConsoleOutput("[$arrStr]")
            } else {
                return try {
                    val indexExpr = parts[1].split("]")[0]
                    val index = ParserMath.parseMathExpression(
                        indexExpr,
                        resolve = { name ->
                            Value(name).process(variables, arrays)
                        }
                    )
                    if (index < 0 || index >= it.values.size)
                        ConsoleOutput("", E("Array index out of bounds", id))
                    else
                        ConsoleOutput(it.values[index].value)
                } catch (e: Exception) {
                    ConsoleOutput("", E("${e.message}", id))
                }
            }
        }
    }

    return try {
        val result = ParserMath.parseMathExpression(
            value.value,
            resolve = { name ->
                Value(name).process(variables, arrays)
            }
        )
        ConsoleOutput(result.toString())
    } catch (e: Exception) {
        ConsoleOutput("", E("${e.message}", id))
    }
}

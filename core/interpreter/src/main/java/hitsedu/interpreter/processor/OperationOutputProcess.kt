package hitsedu.interpreter.processor

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserLogic
import hitsedu.interpreter.syntax.ParserMath
import hitsedu.interpreter.syntax.Validator
import hitsedu.interpreter.utils.Operators
import hitsedu.interpreter.utils.Type

fun OperationOutput.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>
): ConsoleOutput {

    fun resolve(v: String): Any {
        when {
            v == "true" -> return true
            v == "false" -> return false
            v.toIntOrNull() != null -> return v.toInt()
            v.toDoubleOrNull() != null -> return v.toDouble()
            v.startsWith("\"") && v.endsWith("\"") -> return v.trim('"')
        }

        variables.find { it.name == v }?.value?.let { value ->
            return when (value.type) {
                Type.BOOLEAN -> value.value.toBoolean()
                Type.INT -> value.value.toInt()
                Type.DOUBLE -> value.value.toDouble()
                Type.LOGIC -> ParserLogic.parseLogicExpression(value.value, ::resolve)
                Type.MATH -> ParserMath.parseMathExpression(value.value, ::resolve)
                else -> value.value
            }
        }

        if (v.contains("[") && v.endsWith("]")) {
            val arrayName = v.substringBefore("[")
            val indexExpr = v.substringAfter("[").substringBefore("]")

            val index = when {
                indexExpr.toIntOrNull() != null -> indexExpr.toInt()
                else -> ParserMath.parseMathExpression(indexExpr, ::resolve).toInt()
            }

            return arrays.find { it.name == arrayName }?.values?.getOrNull(index)?.let { value ->
                when (value.type) {
                    Type.BOOLEAN -> value.value.toBoolean()
                    Type.INT -> value.value.toInt()
                    Type.DOUBLE -> value.value.toDouble()
                    Type.LOGIC -> ParserLogic.parseLogicExpression(value.value, ::resolve)
                    Type.MATH -> ParserMath.parseMathExpression(value.value, ::resolve)
                    else -> value.value
                }
            } ?: error("Array element not found: $v")
        }

        error("Cannot resolve value: $v")
    }

    if (!value.value.contains("[") || value.value.endsWith("]") && value.value.count { it == '[' } == 1) {
        arrays.forEach { array ->
            if (value.value.startsWith("${array.name}[")) {
                try {
                    val indexStr = value.value.substringAfter("[").substringBefore("]")
                    val index = if (indexStr.toIntOrNull() != null) {
                        indexStr.toInt()
                    } else {
                        ParserMath.parseMathExpression(indexStr, ::resolve).toInt()
                    }

                    if (index < 0 || index >= array.values.size) {
                        return ConsoleOutput("", E("Array index out of bounds", id))
                    }
                    return ConsoleOutput(array.values[index].value)
                } catch (e: Exception) {
                    return ConsoleOutput("", E("Invalid array index: ${e.message}", id))
                }
            } else if (value.value == array.name) {
                val out = array.values.joinToString(", ") { it.value }
                return ConsoleOutput("[$out]")
            }
        }
    }

    return try {
        when (Validator.validate(value.value)) {
            Type.MATH -> {
                val result = ParserMath.parseMathExpression(value.value, ::resolve)
                ConsoleOutput(result.toString())
            }
            Type.LOGIC -> {
                val result = ParserLogic.parseLogicExpression(value.value, ::resolve)
                ConsoleOutput(result.toString())
            }
            else -> {
                val resolved = resolve(value.value)
                ConsoleOutput(resolved.toString())
            }
        }
    } catch (e: Exception) {
        ConsoleOutput("", E("Error processing expression: ${e.message}", id))
    }
}

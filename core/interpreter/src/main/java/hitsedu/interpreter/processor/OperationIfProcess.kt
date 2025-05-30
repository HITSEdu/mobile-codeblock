package hitsedu.interpreter.processor

import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationIf
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserLogic
import hitsedu.interpreter.syntax.ParserMath
import hitsedu.interpreter.utils.Type

fun OperationIf.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>,
): Boolean? {
    fun resolve(v: String): Any {
        when {
            v == "true" -> return true
            v == "false" -> return false
            v.toIntOrNull() != null -> return v.toInt()
            v.toDoubleOrNull() != null -> return v.toDouble()
        }

        if (v.contains("[") && v.endsWith("]")) {
            val arrayName = v.substringBefore("[")
            val indexExpr = v.substringAfter("[").substringBefore("]")

            val index = when {
                indexExpr.toIntOrNull() != null -> indexExpr.toInt()
                else -> ParserMath.parseMathExpression(indexExpr) { name ->
                    variables.find { it.name == name }?.value?.value?.toDoubleOrNull()
                        ?: error("Cannot resolve variable: $name")
                }.toInt()
            }

            return arrays.find { it.name == arrayName }?.values?.getOrNull(index)?.let { value ->
                when (value.type) {
                    Type.BOOLEAN -> value.value.toBoolean()
                    Type.INT -> value.value.toInt()
                    Type.DOUBLE -> value.value.toDouble()
                    else -> error("Array element must be number or boolean")
                }
            } ?: error("Array element not found: $v")
        }

        variables.find { it.name == v }?.value?.let { value ->
            return when (value.type) {
                Type.BOOLEAN -> value.value.toBoolean()
                Type.INT -> value.value.toInt()
                Type.DOUBLE -> value.value.toDouble()
                else -> error("Variable must be number or boolean")
            }
        }

        error("Cannot resolve value: $v")
    }

    return try {
        val result = ParserLogic.parseLogicExpression(this.value.value, ::resolve)
        println("[IF DEBUG] Condition '${this.value.value}' evaluated to $result")
        result
    } catch (e: Exception) {
        println("Error evaluating if condition '${this.value.value}': ${e.message}")
        null
    }
}
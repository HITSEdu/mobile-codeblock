package hitsedu.interpreter.processor

import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.syntax.ParserLogic
import hitsedu.interpreter.syntax.ParserMath
import hitsedu.interpreter.syntax.Validator
import hitsedu.interpreter.utils.Type

fun Value.process(
    variables: MutableList<OperationVariable>,
    arrays: MutableList<OperationArray>,
): Value? {
    val type = try {
        Validator.validate(value)
    } catch (e: Exception) {
        return null
    }

    fun resolve(v: String): Any {
        when {
            v == "true" -> return true
            v == "false" -> return false
            v.toIntOrNull() != null -> return v.toInt()
            v.toDoubleOrNull() != null -> return v.toDouble()
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

            val indexValue = try {
                ParserMath.parseMathExpression(indexExpr) { name ->
                    variables.find { it.name == name }?.value?.value?.toDoubleOrNull()
                        ?: error("Cannot resolve variable: $name")
                }.toInt()
            } catch (e: Exception) {
                error("Error processing array index: ${e.message}")
            }

            return arrays.find { it.name == arrayName }?.values?.getOrNull(indexValue)?.let { value ->
                when (value.type) {
                    Type.BOOLEAN -> value.value.toBoolean()
                    Type.INT -> value.value.toInt()
                    Type.DOUBLE -> value.value.toDouble()
                    else -> error("Array element must be number or boolean")
                }
            } ?: error("Array element not found: $v")
        }

        error("Cannot resolve value: $v")
    }

    return when (type) {
        Type.MATH -> {
            val result = ParserMath.parseMathExpression(value, ::resolve)
            this.copy(value = result.toString(), type = fromValue(result))
        }

        Type.LOGIC -> {
            val result = ParserLogic.parseLogicExpression(value, ::resolve)
            this.copy(value = result.toString(), type = fromValue(result))
        }

        Type.VARIABLE -> {
            val resolved = resolve(value)
            this.copy(value = resolved.toString(), type = fromValue(resolved))
        }

        Type.ARRAY_ACCESS -> {
            val resolved = resolve(value)
            this.copy(value = resolved.toString(), type = fromValue(resolved))
        }

        Type.BOOLEAN, Type.INT, Type.DOUBLE, Type.STRING ->
            this.copy(type = type)

        Type.UNKNOWN -> {
            val resolved = resolve(value)
            this.copy(value = resolved.toString(), type = fromValue(resolved))
        }
    }
}

fun fromValue(value: Any): Type {
    return when (value) {
        is Boolean -> Type.BOOLEAN
        is Int -> Type.INT
        is Double -> Type.DOUBLE
        else -> Type.STRING
    }
}
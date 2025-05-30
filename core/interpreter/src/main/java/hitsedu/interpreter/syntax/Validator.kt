package hitsedu.interpreter.syntax

import hitsedu.interpreter.utils.Type

object Validator {
    fun validate(token: String): Type = when {
        token == "true" || token == "false" -> Type.BOOLEAN
        token.startsWith("\"") && token.endsWith("\"") -> Type.STRING
        token.toIntOrNull() != null -> Type.INT
        token.toDoubleOrNull() != null -> Type.DOUBLE
        else -> error("Unknown type: $token")
    }
}
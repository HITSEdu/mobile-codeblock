package hitsedu.interpreter.syntax

import hitsedu.interpreter.utils.Operators
import hitsedu.interpreter.utils.Type

object Validator {
    fun validate(token: String): Type {
        return when {
            Operators.LOGIC.keys.any { token.contains(it) } -> Type.LOGIC
            Operators.MATH.keys.any { token.contains(it) } -> Type.MATH
            token.matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*")) -> Type.VARIABLE
            token.matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*\\[\\d+]")) -> Type.ARRAY_ACCESS
            token == "true" || token == "false" -> Type.BOOLEAN
            token.startsWith("\"") && token.endsWith("\"") -> Type.STRING
            token.toIntOrNull() != null -> Type.INT
            token.toDoubleOrNull() != null -> Type.DOUBLE
            else -> Type.UNKNOWN
        }
    }
}
package hitsedu.interpreter.syntax

import hitsedu.interpreter.syntax.ParserMath.parseMathExpression
import hitsedu.interpreter.syntax.Tokenizer.tokenize

object Parser {
    fun parseAssignment(
        exp: String,
        resolve: (String) -> Int,
        assignVar: (String, Int) -> Unit,
        assignArray: (String, Int, Int) -> Unit
    ): Int {
        val tokens = tokenize(exp)
        val assignIndex = tokens.indexOf("=")

        if (assignIndex > 0) {
            val leftPart = tokens.subList(0, assignIndex).joinToString("")
            val rightPart = tokens.subList(assignIndex + 1, tokens.size).joinToString("")

            when {
                leftPart.isVariable() -> {
                    val value = parseMathExpression(rightPart, resolve)
                    assignVar(leftPart, value)
                    return value
                }

                leftPart.isArrayAccess() -> {
                    val (arrayName, index) = parseArrayAccess(leftPart)
                    val value = parseMathExpression(rightPart, resolve)
                    assignArray(arrayName, index, value)
                    return value
                }
            }
        }

        return parseMathExpression(exp, resolve)
    }

    private fun parseArrayAccess(access: String): Pair<String, Int> {
        val regex = Regex("""([a-zA-Z_][a-zA-Z0-9_]*)\[(\d+)]""")
        val match = regex.matchEntire(access) ?: error("Invalid array access syntax")
        return match.groupValues[1] to match.groupValues[2].toInt()
    }


    private fun String.isVariable() = matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*"))
    private fun String.isArrayAccess() = matches(Regex("""[a-zA-Z_][a-zA-Z0-9_]*\[\d+]"""))
}
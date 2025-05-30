package hitsedu.interpreter.syntax

import hitsedu.interpreter.syntax.Handler.handleClosingParenthesis
import hitsedu.interpreter.syntax.Handler.handleOperator

import hitsedu.interpreter.syntax.ParserMath.parseMathExpression
import hitsedu.interpreter.syntax.Tokenizer.tokenizeLogic
import hitsedu.interpreter.utils.Operators.LOGIC
import java.util.Stack

object ParserLogic {
    fun parseLogicExpression(exp: String, resolve: (String) -> Any): Boolean {
        val output = mutableListOf<String>()
        val stack = Stack<String>()
        if (Regex("""\b\w+\s*\[\s*[^]]+\s*\]""").findAll(exp).map { it.value }.toList().isNotEmpty()) {
            return processLogicWithArrays(exp, resolve)
        }
        val tokens = Tokenizer.tokenizeLogic(exp)

        for (token in tokens) {
            when {
                token.isOperand() -> output.add(token)
                token in LOGIC -> Handler.handleOperator(token, stack, output, LOGIC)
                token == "(" -> stack.push(token)
                token == ")" -> Handler.handleClosingParenthesis(stack, output)
            }
        }

        while (stack.isNotEmpty()) {
            val op = stack.pop()
            if (op == "(" || op == ")") error("Mismatched parentheses")
            output.add(op)
        }

        return evaluateLogicRPN(output, resolve)
    }

    private fun evaluateLogicRPN(rpn: List<String>, resolve: (String) -> Any): Boolean {
        val stack = Stack<Any>()
        for (token in rpn) {
            when {
                token.isLogicOperator() -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(applyLogicOp(a, b, token))
                }

                token == "true" -> stack.push(true)
                token == "false" -> stack.push(false)
                token.toIntOrNull() != null -> stack.push(token.toInt())
                token.toDoubleOrNull() != null -> stack.push(token.toDouble())
                else -> stack.push(resolve(token))
            }
        }
        return stack.pop() as? Boolean ?: error("Expression did not evaluate to a boolean")
    }

    private fun applyLogicOp(a: Any, b: Any, op: String): Boolean {
        return when (op) {
            "==" -> a == b
            "!=" -> a != b
            "<", ">", "<=", ">=" -> {
                when {
                    a is Number && b is Number -> when (op) {
                        "<" -> a.toDouble() < b.toDouble()
                        ">" -> a.toDouble() > b.toDouble()
                        "<=" -> a.toDouble() <= b.toDouble()
                        ">=" -> a.toDouble() >= b.toDouble()
                        else -> false
                    }

                    else -> error("Comparison operators can only be used with numbers")
                }
            }

            "&&", "||" -> {
                val aBool = when (a) {
                    is Boolean -> a
                    is Number -> error("Logical operators can only be used with booleans")
                    else -> error("Logical operators can only be used with booleans")
                }
                val bBool = when (b) {
                    is Boolean -> b
                    is Number -> error("Logical operators can only be used with booleans")
                    else -> error("Logical operators can only be used with booleans")
                }
                when (op) {
                    "&&" -> aBool && bBool
                    "||" -> aBool || bBool
                    else -> false
                }
            }

            else -> error("Unsupported logic operator $op")
        }
    }

    private fun String.isOperand() = !LOGIC.containsKey(this) && this != "(" && this != ")" && this != "["
    private fun String.isLogicOperator() = this in LOGIC

    private fun processLogicWithArrays(
        expression: String,
        resolve: (String) -> Any
    ): Boolean {
        val arrayPattern = Regex("""\b\w+\s*\[\s*[^\]]+\s*\]""")

        var processedExpression = expression
        arrayPattern.findAll(expression).forEach { match ->
            val arrayAccess = match.value
            val resolvedValue = resolve(arrayAccess)
            processedExpression = processedExpression.replace(
                arrayAccess,
                when (resolvedValue) {
                    is Number -> resolvedValue.toString()
                    is Boolean -> resolvedValue.toString()
                    else -> error("Array element must be number or boolean")
                }
            )
        }

        return parseLogicExpression(processedExpression, resolve)
    }
}
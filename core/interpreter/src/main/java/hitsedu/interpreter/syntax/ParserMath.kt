package hitsedu.interpreter.syntax

import hitsedu.interpreter.syntax.Handler.handleClosingParenthesis
import hitsedu.interpreter.syntax.Handler.handleOperator
import hitsedu.interpreter.syntax.Tokenizer.tokenize
import hitsedu.interpreter.utils.Operators.MATH
import java.util.Stack

object ParserMath {
    fun parseMathExpression(exp: String, resolve: (String) -> Int): Int {
        val output = mutableListOf<String>()
        val stack = Stack<String>()
        val tokens = tokenize(exp)

        for (token in tokens) {
            when {
                token.isNumber() || token.isVariable() -> output.add(token)
                token in MATH -> handleOperator(token, stack, output, MATH)
                token == "(" -> stack.push(token)
                token == ")" -> handleClosingParenthesis(stack, output)
            }
        }

        while (stack.isNotEmpty()) {
            val op = stack.pop()
            if (op == "(" || op == ")") error("Mismatched parentheses")
            output.add(op)
        }

        return evaluateMathRPN(output, resolve)
    }


    private fun evaluateMathRPN(rpn: List<String>, resolve: (String) -> Int): Int {
        val stack = Stack<Int>()
        for (token in rpn) {
            when {
                token.isNumber() -> stack.push(token.toInt())
                token.isVariable() -> stack.push(resolve(token))
                token in MATH -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(applyMathOp(a, b, token))
                }
            }
        }
        return stack.pop()
    }

    private fun applyMathOp(a: Int, b: Int, op: String): Int = when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> error("Unknown math operator $op")
    }

    private fun String.isNumber() = toIntOrNull() != null
    private fun String.isVariable() = matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*"))
}
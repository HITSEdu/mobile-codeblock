package hitsedu.interpreter.syntax

import hitsedu.interpreter.syntax.Handler.handleClosingParenthesis
import hitsedu.interpreter.syntax.Handler.handleOperator

import hitsedu.interpreter.syntax.ParserMath.parseMathExpression
import hitsedu.interpreter.syntax.Tokenizer.tokenizeLogic
import hitsedu.interpreter.utils.Operators.LOGIC
import java.util.Stack

object ParserLogic {
    fun parseLogicExpression(exp: String, resolve: (String) -> Int): Boolean {
        val output = mutableListOf<String>()
        val stack = Stack<String>()
        val tokens = tokenizeLogic(exp)

        for (token in tokens) {
            when {
                token.isOperand() -> output.add(token)
                token in LOGIC -> handleOperator(token, stack, output, LOGIC)
                token == "(" -> stack.push(token)
                token == ")" -> handleClosingParenthesis(stack, output)
            }
        }

        while (stack.isNotEmpty()) {
            val op = stack.pop()
            if (op == "(" || op == ")") error("Mismatched parentheses")
            output.add(op)
        }

        return evaluateLogicRPN(output, resolve)
    }


    private fun evaluateLogicRPN(rpn: List<String>, resolve: (String) -> Int): Boolean {
        val stack = Stack<Any>()
        for (token in rpn) {
            when {
                token.isLogicOperator() -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(applyLogicOp(a, b, token, resolve))
                }

                else -> stack.push(token)
            }
        }
        return stack.pop() as Boolean
    }

    private fun applyLogicOp(a: Any, b: Any, op: String, resolve: (String) -> Int): Boolean {
        fun evalOperand(x: Any): Int = when (x) {
            is String -> parseMathExpression(x, resolve)
            is Int -> x
            else -> error("Invalid operand type")
        }

        return when (op) {
            "==" -> evalOperand(a) == evalOperand(b)
            "!=" -> evalOperand(a) != evalOperand(b)
            "<" -> evalOperand(a) < evalOperand(b)
            ">" -> evalOperand(a) > evalOperand(b)
            "<=" -> evalOperand(a) <= evalOperand(b)
            ">=" -> evalOperand(a) >= evalOperand(b)
            "&&" -> (a as Boolean) && (b as Boolean)
            "||" -> (a as Boolean) || (b as Boolean)
            else -> error("Unsupported logic operator $op")
        }
    }

    private fun String.isOperand() = matches(Regex("[a-zA-Z0-9_()+\\-*/]+"))
    private fun String.isLogicOperator() = this in LOGIC
}
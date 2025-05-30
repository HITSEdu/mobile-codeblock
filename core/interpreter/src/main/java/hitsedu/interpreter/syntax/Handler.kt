package hitsedu.interpreter.syntax

import java.util.Stack

object Handler {
    fun handleOperator(
        token: String,
        stack: Stack<String>,
        output: MutableList<String>,
        operators: Map<String, Int>
    ) {
        while (stack.isNotEmpty() &&
            stack.peek() in operators &&
            operators[token]!! <= operators[stack.peek()]!!
        ) {
            output.add(stack.pop())
        }
        stack.push(token)
    }

    fun handleClosingParenthesis(stack: Stack<String>, output: MutableList<String>) {
        while (stack.isNotEmpty() && stack.peek() != "(") {
            output.add(stack.pop())
        }
        if (stack.isNotEmpty() && stack.peek() == "(") {
            stack.pop()
        } else {
            error("Mismatched parentheses")
        }
    }
}
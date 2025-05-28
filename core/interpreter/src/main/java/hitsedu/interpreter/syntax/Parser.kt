package hitsedu.interpreter.syntax

import hitsedu.interpreter.utils.Operators.LOGIC
import hitsedu.interpreter.utils.Operators.MATH
import java.util.Stack

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

    private fun handleOperator(
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

    private fun handleClosingParenthesis(stack: Stack<String>, output: MutableList<String>) {
        while (stack.isNotEmpty() && stack.peek() != "(") {
            output.add(stack.pop())
        }
        if (stack.isNotEmpty() && stack.peek() == "(") {
            stack.pop()
        } else {
            error("Mismatched parentheses")
        }
    }

    private fun String.isNumber() = toIntOrNull() != null
    private fun String.isVariable() = matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*"))
    private fun String.isArrayAccess() = matches(Regex("""[a-zA-Z_][a-zA-Z0-9_]*\[\d+]"""))
    private fun String.isOperand() = matches(Regex("[a-zA-Z0-9_()+\\-*/]+"))
    private fun String.isLogicOperator() = this in LOGIC

    private fun tokenize(exp: String): List<String> {
        val result = mutableListOf<String>()
        val buffer = StringBuilder()
        var i = 0

        while (i < exp.length) {
            val c = exp[i]
            when {
                c.isWhitespace() -> {
                    i++
                }

                c.isDigit() || c.isLetter() || c == '_' -> {
                    buffer.append(c)
                    i++
                }

                else -> {
                    if (buffer.isNotEmpty()) {
                        result.add(buffer.toString())
                        buffer.clear()
                    }
                    result.add(c.toString())
                    i++
                }
            }
        }

        if (buffer.isNotEmpty()) result.add(buffer.toString())
        return result
    }

    private fun tokenizeLogic(exp: String): List<String> {
        val tokens = mutableListOf<String>()
        val buffer = StringBuilder()
        var i = 0

        while (i < exp.length) {
            val c = exp[i]
            if (c.isWhitespace()) {
                i++
                continue
            }

            if (i + 1 < exp.length) {
                val twoChar = exp.substring(i, i + 2)
                if (twoChar in LOGIC) {
                    if (buffer.isNotEmpty()) {
                        tokens.add(buffer.toString())
                        buffer.clear()
                    }
                    tokens.add(twoChar)
                    i += 2
                    continue
                }
            }

            if ("()<>!=&|".contains(c)) {
                if (buffer.isNotEmpty()) {
                    tokens.add(buffer.toString())
                    buffer.clear()
                }
                tokens.add(c.toString())
            } else {
                buffer.append(c)
            }
            i++
        }

        if (buffer.isNotEmpty()) tokens.add(buffer.toString())
        return tokens
    }
}
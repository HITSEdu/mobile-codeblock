package hitsedu.interpreter.syntax

import hitsedu.interpreter.utils.Operators.LOGIC

object Tokenizer {
    fun tokenize(exp: String): List<String> {
        val result = mutableListOf<String>()
        val buffer = StringBuilder()
        var i = 0

        while (i < exp.length) {
            val c = exp[i]
            when {
                c.isWhitespace() -> i++

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

    fun tokenizeLogic(exp: String): List<String> {
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
package hitsedu.interpreter.utils

object Operators {
    val MATH = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2,
        "=" to 0,
    )

    val LOGIC = mapOf(
        "||" to 1,
        "&&" to 2,
        "==" to 3,
        "!=" to 3,
        "<" to 3,
        ">" to 3,
        "<=" to 3,
        ">=" to 3,
//        "true" to 4,
//        "false" to 4,
    )
}
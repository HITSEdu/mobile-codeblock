package hitsedu.interpreter.models

import hitsedu.interpreter.utils.Type

data class Value(
    val value: String = "",
    val id: Long = 0,
    val type: Type? = null,
)
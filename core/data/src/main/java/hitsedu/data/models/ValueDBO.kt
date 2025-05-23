package hitsedu.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ValueDBO(
    val value: String = "",
    val id: Long = 0,
)
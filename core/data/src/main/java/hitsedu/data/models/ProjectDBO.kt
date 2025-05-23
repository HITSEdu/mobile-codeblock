package hitsedu.data.models

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ProjectDBO(
    val caption: String,
    val scale: Float,
    val scopeDBOS: List<ScopeDBO>,
    val globalScope: ScopeDBO,
    val id: Long = Random.nextLong(1, Long.MAX_VALUE),
)
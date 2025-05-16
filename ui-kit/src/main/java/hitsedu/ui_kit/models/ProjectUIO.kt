package hitsedu.ui_kit.models

import kotlin.random.Random

data class ProjectUIO(
    val caption: String,
    val scale: Float,
    val scopeUIOS: List<ScopeUIO>,
    val id: Long = Random.nextLong(1, Long.MAX_VALUE),
)
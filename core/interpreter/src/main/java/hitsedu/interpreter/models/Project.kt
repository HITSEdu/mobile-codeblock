package hitsedu.interpreter.models

import kotlin.random.Random

data class Project(
    val caption: String,
    val scale: Float,
    val scopes: List<Scope>,
    val globalScope: Scope,
    val id: Long = Random.nextLong(1, Long.MAX_VALUE),
)
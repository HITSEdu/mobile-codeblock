package hitsedu.ui_kit.models

import kotlin.random.Random

data class ValueUIO(
    val value: String = "",
    val id: Long = Random.nextLong(1, Long.MAX_VALUE),
)
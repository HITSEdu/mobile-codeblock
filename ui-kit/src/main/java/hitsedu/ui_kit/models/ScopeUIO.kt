package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.operation.OperationUIO
import kotlin.random.Random

data class ScopeUIO(
    val operationUIOS: List<OperationUIO>,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val id: Long = Random.nextLong(1, Long.MAX_VALUE),
)
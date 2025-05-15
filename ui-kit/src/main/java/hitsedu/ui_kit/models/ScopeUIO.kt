package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.operation.OperationUIO

data class ScopeUIO(
    val operationUIOS: List<OperationUIO>,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
)
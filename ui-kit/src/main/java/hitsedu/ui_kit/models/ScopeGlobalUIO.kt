package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationUIO

data class ScopeGlobalUIO(
    override val operationUIOS: List<OperationUIO>,
    override val id: Long,
    val variableUIOS: List<OperationUIO>,
    val arrayUIOS: List<OperationArrayUIO>,
) : ScopeUIO(operationUIOS, id)
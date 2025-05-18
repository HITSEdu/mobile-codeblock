package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

data class ScopeGlobalUIO(
    override val operationUIOS: List<OperationUIO>,
    override val id: Long,
    val variableUIOS: List<OperationVariableUIO>,
    val arrayUIOS: List<OperationArrayUIO>,
) : ScopeUIO(operationUIOS, id)
package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.operation.OperationUIO

open class ScopeUIO(
    open val operationUIOS: List<OperationUIO>,
    open val id: Long = 0,
)
package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

sealed class ScopeUIO {
    abstract val operationUIOS: List<OperationUIO>
}
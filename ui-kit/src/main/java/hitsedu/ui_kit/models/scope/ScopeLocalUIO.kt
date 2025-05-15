package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

sealed class ScopeLocalUIO : ScopeUIO() {
    abstract override val operationUIOS: List<OperationUIO>
    abstract val childScopeUIOS: List<ScopeUIO>
}
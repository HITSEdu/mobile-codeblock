package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

data class ForScopeUIO(
    override val operationUIOS: List<OperationUIO>,
    override val childScopeUIOS: List<ScopeUIO>,
) : ScopeLocalUIO()
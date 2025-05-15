package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

data class ScopeConditionUIO(
    override val operationUIOS: List<OperationUIO>,
    override val childScopeUIOS: List<ScopeUIO>,
) : ScopeLocalUIO()
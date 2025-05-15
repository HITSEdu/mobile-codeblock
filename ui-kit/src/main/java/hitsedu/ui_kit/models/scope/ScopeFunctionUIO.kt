package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

data class ScopeFunctionUIO(
    override val operationUIOS: List<OperationUIO>,
    override val childScopeUIOS: List<ScopeLocalUIO>,
    val offsetX: Float,
    val offsetY: Float,
) : ScopeLocalUIO()
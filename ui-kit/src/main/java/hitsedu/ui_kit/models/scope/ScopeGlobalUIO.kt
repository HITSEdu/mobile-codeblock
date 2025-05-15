package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.OperationUIO

data class ScopeGlobalUIO(
    override val operationUIOS: List<OperationUIO>,
    val functions: List<ScopeFunctionUIO>,
) : ScopeUIO()
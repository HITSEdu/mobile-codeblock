package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ScopeUIO

data class OperationElseUIO(
    val scope: ScopeUIO,
    override val id: Long = 0,
) : OperationUIO(id)
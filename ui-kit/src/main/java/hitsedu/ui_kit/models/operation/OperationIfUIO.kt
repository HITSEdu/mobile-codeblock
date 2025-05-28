package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO

data class OperationIfUIO(
    val scope: ScopeUIO,
    val value: ValueUIO,
    override val e: String? = null,
    override val id: Long = 0,
) : OperationUIO(id)
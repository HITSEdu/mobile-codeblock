package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO

data class OperationForUIO(
    val scope: ScopeUIO,
    val variable: OperationVariableUIO,
    val condition: ValueUIO,
    val value: ValueUIO,
    override val id: Long = 0,
) : OperationUIO(id)
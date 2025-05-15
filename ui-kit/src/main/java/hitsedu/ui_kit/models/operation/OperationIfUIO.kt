package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO

data class OperationIfUIO(
    val scope: ScopeUIO,
    val value: ValueUIO,
) : OperationUIO()
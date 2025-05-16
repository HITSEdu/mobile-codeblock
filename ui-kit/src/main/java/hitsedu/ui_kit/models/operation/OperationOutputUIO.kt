package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ValueUIO

data class OperationOutputUIO(
    val value: ValueUIO,
    override val id: Long = 0,
) : OperationUIO(id)
package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ValueUIO

data class OperationArrayUIO(
    val name: String,
    val size: Int,
    val values: List<ValueUIO>,
    override val id: Long = 0,
) : OperationUIO(id)
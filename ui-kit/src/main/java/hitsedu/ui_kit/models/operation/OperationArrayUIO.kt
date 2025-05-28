package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ValueUIO

data class OperationArrayUIO(
    val name: String,
    val size: Int = 0,
    val values: List<ValueUIO>,
    override val e: String? = null,
    override val id: Long = 0,
) : OperationUIO(id)
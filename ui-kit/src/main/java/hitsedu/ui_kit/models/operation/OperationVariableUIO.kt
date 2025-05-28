package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ValueUIO


data class OperationVariableUIO(
    val name: String,
    val value: ValueUIO,
    override val e: String? = null,
    override val id: Long = 0,
) : OperationUIO(id)
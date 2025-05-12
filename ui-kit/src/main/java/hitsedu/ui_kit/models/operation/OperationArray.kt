package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.data.DataType

data class OperationArray(
    val name: String,
    val size: Int,
    val type: DataType,
    val values: List<OperationValue>,
) : Operation()
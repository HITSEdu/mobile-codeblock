package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.data.DataType

data class OperationUIOArray(
    val name: String,
    val size: Int,
    val type: DataType,
    val values: List<OperationUIOValue>,
) : OperationUIO()
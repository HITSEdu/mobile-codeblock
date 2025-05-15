package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.data.DataType


data class OperationUIOVariable(
    val name: String,
    val type: DataType,
    val value: OperationUIOValue,
) : OperationUIO()
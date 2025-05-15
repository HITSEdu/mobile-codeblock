package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.ValueUIO


data class OperationVariableUIO(
    val name: String,
    val value: ValueUIO,
) : OperationUIO()
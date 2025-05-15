package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.data.DataType

data class OperationArray(
    val name: String,
    val size: Int,
    val type: DataType,
    val values: List<OperationValue>,
) : Operation()
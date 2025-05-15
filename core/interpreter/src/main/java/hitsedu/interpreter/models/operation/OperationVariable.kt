package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.data.DataType


data class OperationVariable(
    val name: String,
    val type: DataType,
    val value: OperationValue,
) : Operation()
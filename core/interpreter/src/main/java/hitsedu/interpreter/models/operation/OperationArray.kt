package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.Value

data class OperationArray(
    val name: String,
    val size: Int = 0,
    val values: List<Value>,
    override val id: Long = 0,
) : Operation(id)
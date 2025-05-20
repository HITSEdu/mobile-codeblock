package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.Value

data class OperationArrayIndex(
    val name: String,
    val index: Value,
    val value: Value,
    override val id: Long = 0,
) : Operation(id)
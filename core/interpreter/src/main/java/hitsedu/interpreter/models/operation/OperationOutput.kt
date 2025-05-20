package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.Value

data class OperationOutput(
    val value: Value,
    override val id: Long = 0,
) : Operation(id)
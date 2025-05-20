package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value

data class OperationIf(
    val scope: Scope,
    val value: Value,
    override val id: Long = 0,
) : Operation(id)
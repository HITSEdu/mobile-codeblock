package hitsedu.interpreter.models.scope

import hitsedu.interpreter.models.operation.Operation

data class ScopeGlobal(
    override val operations: List<Operation>,
    val functions: List<ScopeLocalFunction>,
) : Scope()
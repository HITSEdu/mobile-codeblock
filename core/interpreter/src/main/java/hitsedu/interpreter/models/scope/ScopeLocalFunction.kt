package hitsedu.interpreter.models.scope

import hitsedu.interpreter.models.operation.Operation

data class ScopeLocalFunction(
    override val operations: List<Operation>,
    override val childScopes: List<ScopeLocal>,
) : ScopeLocal()
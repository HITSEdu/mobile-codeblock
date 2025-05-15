package hitsedu.interpreter.models.scope

import hitsedu.interpreter.models.operation.Operation

data class ScopeLocalFor(
    override val operations: List<Operation>,
    override val childScopes: List<Scope>,
) : ScopeLocal()
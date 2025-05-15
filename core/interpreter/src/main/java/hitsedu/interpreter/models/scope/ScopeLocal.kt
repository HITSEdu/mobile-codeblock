package hitsedu.interpreter.models.scope

import hitsedu.interpreter.models.operation.Operation

sealed class ScopeLocal : Scope() {
    abstract override val operations: List<Operation>
    abstract val childScopes: List<Scope>
}
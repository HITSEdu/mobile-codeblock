package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.Operation

sealed class LocalScope : Scope() {
    abstract override val operations: List<Operation>
    abstract val childScopes: List<Scope>
}
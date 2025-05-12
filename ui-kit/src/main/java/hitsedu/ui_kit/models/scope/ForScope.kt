package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.Operation

data class ForScope(
    override val operations: List<Operation>,
    override val childScopes: List<Scope>,
): LocalScope()
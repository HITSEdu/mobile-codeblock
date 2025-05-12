package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.Operation

data class ConditionScope(
    override val operations: List<Operation>,
    override val childScopes: List<Scope>,
) : LocalScope()
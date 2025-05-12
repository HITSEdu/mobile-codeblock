package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.Operation

data class GlobalScope(
    override val operations: List<Operation>,
    val functions: List<FunctionScope>,
) : Scope()
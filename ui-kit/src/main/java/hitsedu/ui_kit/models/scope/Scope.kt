package hitsedu.ui_kit.models.scope

import hitsedu.ui_kit.models.operation.Operation

sealed class Scope {
    abstract val operations: List<Operation>
}
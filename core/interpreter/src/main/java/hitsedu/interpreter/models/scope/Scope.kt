package hitsedu.interpreter.models.scope

import hitsedu.interpreter.models.operation.Operation

sealed class Scope {
    abstract val operations: List<Operation>
}
package hitsedu.interpreter.models

import hitsedu.interpreter.models.operation.Operation

open class Scope(
    open val operations: List<Operation>,
    open val id: Long = 0,
)
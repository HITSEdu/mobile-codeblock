package hitsedu.interpreter.models

import hitsedu.interpreter.models.operation.Operation

data class Scope(
    val operations: List<Operation>,
    val id: Long = 0,
)
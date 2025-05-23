package hitsedu.data.models

import hitsedu.data.models.operation.OperationDBO
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class ScopeDBO(
    val operationDBOS: List<OperationDBO> = emptyList(),
    val id: Long = 0,
)
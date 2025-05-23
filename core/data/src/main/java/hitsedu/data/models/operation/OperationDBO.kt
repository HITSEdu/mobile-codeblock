package hitsedu.data.models.operation

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class OperationDBO(
    open val id: Long = 0L,
)
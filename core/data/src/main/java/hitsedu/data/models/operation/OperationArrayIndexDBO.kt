package hitsedu.data.models.operation

import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ArrayIndex")
data class OperationArrayIndexDBO(
    val name: String,
    val index: ValueDBO,
    val valueDBO: ValueDBO,
    val id: Long,
) : OperationDBO()
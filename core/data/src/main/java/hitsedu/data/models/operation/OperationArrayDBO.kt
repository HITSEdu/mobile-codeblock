package hitsedu.data.models.operation

import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Array")
data class OperationArrayDBO(
    val name: String,
    val size: Int,
    val valueDBOS: List<ValueDBO>,
    val id: Long,
) : OperationDBO()
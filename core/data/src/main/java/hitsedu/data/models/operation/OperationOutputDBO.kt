package hitsedu.data.models.operation

import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Output")
data class OperationOutputDBO(
    val id: Long,
    val valueDBO: ValueDBO,
) : OperationDBO()
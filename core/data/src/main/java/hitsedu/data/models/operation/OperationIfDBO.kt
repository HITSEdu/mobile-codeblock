package hitsedu.data.models.operation

import hitsedu.data.models.ScopeDBO
import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("If")
data class OperationIfDBO(
    val scopeDBO: ScopeDBO,
    val valueDBO: ValueDBO,
) : OperationDBO()
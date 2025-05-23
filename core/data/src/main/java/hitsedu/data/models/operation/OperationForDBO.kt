package hitsedu.data.models.operation

import hitsedu.data.models.ScopeDBO
import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("For")
data class OperationForDBO(
    val scopeDBO: ScopeDBO,
    val variable: ValueDBO,
    val condition: ValueDBO,
    val valueDBO: ValueDBO,
) : OperationDBO()
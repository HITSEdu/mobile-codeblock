package hitsedu.data.models.operation

import hitsedu.data.models.ScopeDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Else")
data class OperationElseDBO(
    val scopeDBO: ScopeDBO,
) : OperationDBO()
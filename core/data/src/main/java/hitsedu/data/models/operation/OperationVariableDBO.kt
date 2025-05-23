package hitsedu.data.models.operation

import hitsedu.data.models.ValueDBO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Variable")
data class OperationVariableDBO(
    val name: String,
    val valueDBO: ValueDBO,
) : OperationDBO()
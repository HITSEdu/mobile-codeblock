package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.scope.ScopeUIO
import java.util.UUID

data class ProjectUIO(
    val id: UUID,
    val caption: String,
    val type: ProjectType,
    val scale: Float,
    val scopeUIOS: List<ScopeUIO>,
)
package hitsedu.ui_kit.models

import hitsedu.ui_kit.models.scope.Scope
import java.util.UUID

data class ProjectUI(
    val id: UUID,
    val caption: String,
    val type: ProjectType,
    val scale: Float,
    val scopes: List<Scope>,
)
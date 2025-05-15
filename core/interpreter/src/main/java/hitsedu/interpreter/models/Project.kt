package hitsedu.interpreter.models

import hitsedu.interpreter.models.scope.Scope
import java.util.UUID

data class Project(
    val id: UUID,
    val caption: String,
    val type: ProjectType,
    val scale: Float,
    val scopes: List<Scope>,
)
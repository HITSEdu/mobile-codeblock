package hitsedu.ui_kit.models

data class ProjectUIO(
    val id: Long = 0,
    val caption: String,
    val scale: Float,
    val scopeUIOS: List<ScopeUIO>,
)
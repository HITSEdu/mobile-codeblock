package hitsedu.data

import hitsedu.data.models.ProjectDBO
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    val projects: Flow<List<ProjectDBO>>
    suspend fun add(projectDBO: ProjectDBO)
    suspend fun update(projectDBO: ProjectDBO)
    suspend fun delete(id: Long)
    suspend fun get(id: Long): ProjectDBO?
}
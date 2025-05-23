package hitsedu.data

import android.content.Context
import hitsedu.data.models.ProjectDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class ProjectRepositoryImpl(
    private val context: Context,
    private val json: Json = Json
) : ProjectRepository {

    private val dataStore = context.projectDataStore

    override val projects: Flow<List<ProjectDBO>> = dataStore.data
        .map { jsonString ->
            if (jsonString.isBlank()) emptyList()
            else runCatching { json.decodeFromString<List<ProjectDBO>>(jsonString) }.getOrElse { emptyList() }
        }

    override suspend fun add(projectDBO: ProjectDBO) {
        dataStore.updateData { jsonString ->
            val projects = decode(jsonString).toMutableList()
            projects.add(projectDBO)
            json.encodeToString(projects)
        }
    }

    override suspend fun update(projectDBO: ProjectDBO) {
        dataStore.updateData { jsonString ->
            val projects = decode(jsonString).toMutableList()
            val index = projects.indexOfFirst { it.id == projectDBO.id }
            if (index != -1) {
                projects[index] = projectDBO
            }
            json.encodeToString(projects)
        }
    }

    override suspend fun delete(id: Long) {
        dataStore.updateData { jsonString ->
            val projects = decode(jsonString).filterNot { it.id == id }
            json.encodeToString(projects)
        }
    }

    override suspend fun get(id: Long): ProjectDBO? {
        return dataStore.data.firstOrNull()?.let { jsonString ->
            decode(jsonString).find { it.id == id }
        }
    }

    private fun decode(jsonString: String): List<ProjectDBO> {
        return runCatching {
            json.decodeFromString<List<ProjectDBO>>(jsonString)
        }.getOrElse { emptyList() }
    }
}

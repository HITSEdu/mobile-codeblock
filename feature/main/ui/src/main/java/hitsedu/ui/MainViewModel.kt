package hitsedu.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hitsedu.data.ProjectRepository
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.utils.mapper.toProjectDBO
import hitsedu.ui_kit.utils.mapper.toProjectUIO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    fun getRandom(): Long = Random.nextLong(1, Long.MAX_VALUE)

    val projects: StateFlow<List<ProjectUIO>> = repository.projects
        .map { it.map { p -> p.toProjectUIO() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun add(project: ProjectUIO) = viewModelScope.launch {
        repository.add(project.toProjectDBO())
    }

    fun delete(id: Long) = viewModelScope.launch {
        repository.delete(id)
    }

    suspend fun get(id: Long): ProjectUIO? = repository.get(id)?.toProjectUIO()
}
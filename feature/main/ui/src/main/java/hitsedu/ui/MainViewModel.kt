package hitsedu.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hitsedu.data.ProjectRepository
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.utils.mapper.toProjectDBO
import hitsedu.ui_kit.utils.mapper.toProjectUIO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(
    private val repository: ProjectRepository,
) : ViewModel() {

    var state by mutableStateOf<MainScreenState>(MainScreenState.Loading)
        private set

    init {
        loadProjects()
    }

    private fun loadProjects() = viewModelScope.launch {
        state = MainScreenState.Loading
        repository.projects
            .map { list -> list.map { it.toProjectUIO() } }
            .catch { e -> state = MainScreenState.Error(e.localizedMessage ?: "Unknown error") }
            .collect { projects -> state = MainScreenState.Success(projects) }
    }

    fun add(project: ProjectUIO) = viewModelScope.launch {
        repository.add(project.toProjectDBO())
    }

    fun delete(id: Long) = viewModelScope.launch {
        repository.delete(id)
    }

    fun getRandom(): Long = Random.nextLong(1, Long.MAX_VALUE)
}
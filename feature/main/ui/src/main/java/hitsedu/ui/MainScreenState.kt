package hitsedu.ui

import hitsedu.ui_kit.models.ProjectUIO

sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Success(val projects: List<ProjectUIO>) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
}
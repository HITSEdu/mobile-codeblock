package hitsedu.board

import hitsedu.ui_kit.models.ProjectUIO

sealed class BoardScreenState {
    data object Loading : BoardScreenState()
    data class Success(val project: ProjectUIO) : BoardScreenState()
    data class Error(val message: String) : BoardScreenState()
}
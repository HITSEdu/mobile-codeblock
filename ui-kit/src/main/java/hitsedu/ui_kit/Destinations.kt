package hitsedu.ui_kit

object Destinations {
    const val MAIN_SCREEN = "main"
    const val BOARD_SCREEN = "board"
    fun boardScreen(id: Long) = "$BOARD_SCREEN/$id"
}
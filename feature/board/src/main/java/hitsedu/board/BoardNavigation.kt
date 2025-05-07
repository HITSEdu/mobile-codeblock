package hitsedu.board

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import hitsedu.ui_kit.Destinations

fun NavGraphBuilder.boardScreen(
    navController: NavHostController,
) {
    composable(Destinations.BOARD_SCREEN) {
        BoardScreen(navController)
    }
}
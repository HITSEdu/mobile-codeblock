package hitsedu.board.ui

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hitsedu.ui_kit.Destinations

fun NavGraphBuilder.boardScreen(
    navController: NavHostController,
) {
    composable(
        route = "${Destinations.BOARD_SCREEN}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong("id")!!
        BoardScreen(id, navController)
    }
}
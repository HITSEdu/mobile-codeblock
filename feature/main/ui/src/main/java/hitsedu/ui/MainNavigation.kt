package hitsedu.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import hitsedu.ui_kit.Destinations

fun NavGraphBuilder.mainNavigation(
    navController: NavHostController,
) {
    composable(Destinations.MAIN_SCREEN) {
        MainScreen(navController)
    }
}
package hitsedu.mobile_codeblock

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import hitsedu.board.boardScreen
import hitsedu.ui.mainScreen
import hitsedu.ui_kit.Destinations
import hitsedu.ui_kit.theme.MobilecodeblockTheme

@Composable
fun Navigation() {
    MobilecodeblockTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Destinations.MAIN_SCREEN // Destinations.BOARD_SCREEN,
        ) {
            mainScreen(navController)
            boardScreen(navController)
        }
    }
}
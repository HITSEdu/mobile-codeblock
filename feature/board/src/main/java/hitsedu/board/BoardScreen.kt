package hitsedu.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.ButtonRun
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.components.ProjectTitle

@Composable
fun BoardScreen(
    navController: NavHostController,
) {
    BoardScreenUI(navController)
}

@Composable
private fun BoardScreenUI(
    navController: NavHostController,
) {

    var title by remember { mutableStateOf("Main board") }

    Scaffold(
        topBar = { Header() },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ProjectTitle(
                title = title,
                onTitleChange = { title = it },
                onBackClick = { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomContainer {
                ButtonRun(
                    onClick = {
//                        TODO("Run code")
                    }
                )
            }
        }
    }
}
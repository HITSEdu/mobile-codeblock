package hitsedu.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import hitsedu.board.components.Elements
import hitsedu.board.components.FunctionMain
import hitsedu.board.components.MoveWrapper
import hitsedu.board.components.Workspace
import hitsedu.board.components.dnd.DraggableScreen
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.ButtonBoard
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.components.ProjectTitle
import kotlinx.coroutines.launch

@Composable
fun BoardScreen(
    navController: NavHostController,
) {
    val viewModel = BoardViewModel()
    BoardScreenUI(
        viewModel,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoardScreenUI(
    viewModel: BoardViewModel,
    navController: NavHostController,
) {
    var title by remember { mutableStateOf("Main board") }
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    val openBottomSheet: () -> Unit = {
        isBottomSheetVisible = true
        scope.launch { sheetState.show() }
    }

    DraggableScreen {
        Scaffold(
            topBar = {
                Column {
                    Header()
                    ProjectTitle(
                        title = title,
                        onTitleChange = { title = it },
                        onBackClick = { navController.popBackStack() }
                    )
                }
            },
            bottomBar = {
                BottomContainer {
                    ButtonBoard(
                        onClick = {
                            scope.launch {
                                isBottomSheetVisible = true
                                sheetState.expand()
                            }
                        }
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Workspace {
                    MoveWrapper {
                        FunctionMain(
                            viewModel = viewModel,
                        )
                    }
                }
            }
            BottomSheet(
                isBottomSheetVisible = isBottomSheetVisible,
                sheetState = sheetState,
                onDismiss = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible = false }
                }
            ) {
                Elements(
                    viewModel = viewModel,
                    onDragStart = { scope.launch { sheetState.hide() } },
                    onDragStop = { scope.launch { sheetState.expand() } },
                )
            }
        }
    }
}
package hitsedu.board.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import hitsedu.board.ui.components.Console
import hitsedu.board.ui.components.Elements
import hitsedu.board.ui.components.FunctionMain
import hitsedu.board.ui.components.MoveWrapper
import hitsedu.board.ui.components.Workspace
import hitsedu.board.ui.components.dnd.DraggableScreen
import hitsedu.board.ui.utils.BottomSheetClick
import hitsedu.ui_kit.R
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
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var bottomSheetContent = BottomSheetClick.CONSOLE
    val scope = rememberCoroutineScope()

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        ButtonBoard(
                            text = R.string.elements,
                            icon = R.drawable.icon_element,
                            onClick = {
                                bottomSheetContent = BottomSheetClick.ELEMENTS
                                scope.launch {
                                    viewModel.expandBottomSheet()
                                    sheetState.expand()
                                }
                            }
                        )
                        ButtonBoard(
                            text = R.string.console,
                            icon = R.drawable.icon_console,
                            onClick = {
                                bottomSheetContent = BottomSheetClick.CONSOLE
                                scope.launch {
                                    viewModel.expandBottomSheet()
                                    sheetState.expand()
                                }
                            }
                        )
                    }
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
                isBottomSheetVisible = viewModel.isBottomSheetVisible,
                sheetState = sheetState,
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        viewModel.hideBottomSheet()
                    }
                }
            ) {
                when (bottomSheetContent) {
                    BottomSheetClick.ELEMENTS -> Elements(
                        viewModel = viewModel,
                        onDragStart = {
                            scope.launch { sheetState.hide() }
                        },
                    )
                    BottomSheetClick.CONSOLE -> Console(

                    )
                }
            }
        }
    }
}
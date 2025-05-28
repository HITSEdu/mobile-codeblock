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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import hitsedu.board.ui.components.Console
import hitsedu.board.ui.components.Elements
import hitsedu.board.ui.components.FunctionMain
import hitsedu.board.ui.components.MoveWrapper
import hitsedu.board.ui.components.Workspace
import hitsedu.board.ui.components.dnd.DraggableScreen
import hitsedu.board.ui.utils.BottomSheetClick
import hitsedu.data.ProjectRepositoryImpl
import hitsedu.interpreter.InterpreterImpl
import hitsedu.ui_kit.R
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.ButtonBoard
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.components.ProjectTitle
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import kotlinx.coroutines.launch

@Composable
fun BoardScreen(
    id: Long,
    navController: NavHostController,
) {
    val repository = ProjectRepositoryImpl(LocalContext.current)
    val interpreter = InterpreterImpl()
    val viewModel = BoardViewModel(repository, interpreter)
    BoardScreenUI(
        id,
        viewModel,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoardScreenUI(
    id: Long,
    viewModel: BoardViewModel,
    navController: NavHostController,
) {
    viewModel.init(id)
    val project by viewModel.project.collectAsState()

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
                    project?.caption?.let {
                        ProjectTitle(
                            title = it,
                            onTitleChange = {
                                viewModel.setProjectCaption(it)
                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    }
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
                            project = project ?: ProjectUIO(
                                caption = "test",
                                scale = 1f,
                                scopeUIOS = listOf(
                                    ScopeUIO(
                                        operationUIOS = emptyList(),
                                        id = 1488L,
                                    )
                                ),
                                globalScope = ScopeUIO(
                                    operationUIOS = emptyList(),
                                    id = 1488L,
                                ),
                                id = 9L,
                            ),
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
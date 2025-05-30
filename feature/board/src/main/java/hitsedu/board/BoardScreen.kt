package hitsedu.board

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import hitsedu.board.components.Console
import hitsedu.board.components.Elements
import hitsedu.board.components.FunctionMain
import hitsedu.board.components.MoveWrapper
import hitsedu.board.components.Workspace
import hitsedu.board.components.dnd.DraggableScreen
import hitsedu.board.utils.BottomSheetClick
import hitsedu.data.ProjectRepositoryImpl
import hitsedu.ui_kit.R
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.ButtonBoard
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.components.ProjectTitle
import hitsedu.ui_kit.theme.border
import hitsedu.ui_kit.theme.green
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.paddingMedium
import hitsedu.ui_kit.theme.shapeLarge
import hitsedu.ui_kit.theme.size20
import hitsedu.ui_kit.theme.spaceMedium
import kotlinx.coroutines.launch

@Composable
fun BoardScreen(
    id: Long,
    navController: NavHostController,
) {
    val repository = ProjectRepositoryImpl(LocalContext.current)
    val viewModel = BoardViewModel(repository)
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
    val consoleOutput by viewModel.consoleOutput.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.snackbar)
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
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = {
                        Snackbar(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(paddingMedium)
                                .border(
                                    border,
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(shapeLarge),
                                ),
                            shape = RoundedCornerShape(shapeLarge),
                            containerColor = MaterialTheme.colorScheme.background,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = paddingLarge),
                                horizontalArrangement = Arrangement.spacedBy(spaceMedium),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    tint = green,
                                    contentDescription = "SnackBar Icon",
                                    modifier = Modifier.size(size20),
                                )
                                Text(
                                    text = snackbarMessage,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                )
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
                        project?.let {
                            FunctionMain(
                                project = it,
                                viewModel = viewModel,
                                showSnackbar = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(snackbarMessage)
                                    }
                                }
                            )
                        }
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
                        consoleOutput = consoleOutput
                    )
                }
            }
        }
    }
}
package hitsedu.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hitsedu.data.ProjectRepositoryImpl
import hitsedu.ui.components.BottomSection
import hitsedu.ui.components.DocumentationContent
import hitsedu.ui.components.ProjectItem
import hitsedu.ui.components.TemplateItem
import hitsedu.ui_kit.Destinations
import hitsedu.ui_kit.R
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.orange
import hitsedu.ui_kit.theme.paddingExtraLarge
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.purple
import hitsedu.ui_kit.theme.red
import hitsedu.ui_kit.theme.size24
import hitsedu.ui_kit.theme.size312
import hitsedu.ui_kit.theme.spaceMedium
import hitsedu.ui_kit.utils.TemplateBoards
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    val repository = ProjectRepositoryImpl(context = LocalContext.current)
    val viewModel = MainViewModel(repository)
    MainScreenUI(viewModel, navController)
}

@Composable
private fun MainScreenUI(
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    when (val state = viewModel.state) {
        is MainScreenState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = red,
                modifier = Modifier.size(128.dp)
            )
        }

        is MainScreenState.Success -> MainScreenContent(
            projects = state.projects,
            viewModel = viewModel,
            navController = navController,
        )

        is MainScreenState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = state.message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    projects: List<ProjectUIO>,
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    var isTemplatesVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val templatesState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Header()
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = paddingExtraLarge,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.projects),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier,
                )
                IconButton(
                    onClick = {
//                        TODO("Filter")
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(size24),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = paddingExtraLarge,
                    )
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spaceMedium),
            ) {
                items(projects) { p ->
                    ProjectItem(
                        caption = p.caption,
                        onNavigate = {
                            navController.navigate(Destinations.boardScreen(p.id))
                        },
                        onDelete = {
                            viewModel.delete(p.id)
                        },
                    )
                }
            }
            BottomSection(
                onBoardClick = {
                    val newProjectId = Random.nextLong(1, Long.MAX_VALUE)
                    val globalScopeId = Random.nextLong(1, Long.MAX_VALUE)
                    val newProject = ProjectUIO(
                        id = newProjectId,
                        caption = "Project $newProjectId",
                        scale = 1f,
                        globalScope = ScopeUIO(
                            operationUIOS = emptyList(),
                            id = globalScopeId,
                        ),
                        scopeUIOS = listOf(
                            ScopeUIO(
                                operationUIOS = emptyList(),
                                id = globalScopeId,
                            )
                        ),
                    )
                    viewModel.add(newProject)
                    navController.navigate(Destinations.boardScreen(newProjectId))
                },
                onTemplatesClick = {
                    scope.launch { templatesState.expand() }
                        .invokeOnCompletion { isTemplatesVisible = true }
                },
                onInfoClick = {
                    scope.launch { sheetState.expand() }
                        .invokeOnCompletion { isBottomSheetVisible = true }
                }
            )
        }
        BottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { isBottomSheetVisible = false }
            }
        ) {
            DocumentationContent()
        }
        BottomSheet(
            isBottomSheetVisible = isTemplatesVisible,
            sheetState = templatesState,
            onDismiss = {
                scope.launch { templatesState.hide() }
                    .invokeOnCompletion { isTemplatesVisible = false }
            },
        ) {
            val templates = TemplateBoards.templates.map { it }

            val colorsMap = mapOf(
                "Bubble Sort" to blue,
                "Fibonacci" to purple,
                "Factorial" to orange,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = size312)
                    .padding(
                        horizontal = paddingLarge,
                        vertical = paddingSmall,
                    ),
                verticalArrangement = Arrangement.spacedBy(spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(templates) { p ->
                    TemplateItem(
                        color = colorsMap[p.caption] ?: red,
                        caption = p.caption,
                        onNavigate = {
                            navController.navigate(Destinations.boardScreen(p.id))
                        },
                    )
                }
            }
        }
    }
}
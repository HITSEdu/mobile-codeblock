package hitsedu.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import hitsedu.ui.utils.TemplateBoards
import hitsedu.ui_kit.Destinations
import hitsedu.ui_kit.R
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.orange
import hitsedu.ui_kit.theme.pink
import hitsedu.ui_kit.theme.purple
import hitsedu.ui_kit.utils.mapper.toProjectUIO
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    val repository = ProjectRepositoryImpl(context = LocalContext.current)
    val viewModel = MainViewModel(repository)
    MainScreenUI(viewModel, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenUI(
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    val projects = viewModel.projects.collectAsState()

    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isTemplatesVisible by rememberSaveable { mutableStateOf(false) }
    val templatesState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.projects),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                )
                IconButton(
                    onClick = {
//                        TODO("Filter")
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(projects.value) { p ->
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
                    val newProjectId = viewModel.getRandom()
                    val globalScopeId = viewModel.getRandom()
                    val newProject = ProjectUIO(
                        id = newProjectId,
                        caption = "Project $newProjectId",
                        scale = 1f,
                        globalScope = ScopeUIO(
                            operationUIOS = emptyList(),
                            id = globalScopeId
                        ),
                        scopeUIOS = listOf(
                            ScopeUIO(
                                operationUIOS = emptyList(),
                                id = globalScopeId
                            )
                        ),
                    )
                    viewModel.add(newProject)
                    Log.e("Create", newProject.toString())
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
            val templates = TemplateBoards.templates.map { it.toProjectUIO() }

            val colorsMap = mapOf(
                "Bubble Sort" to blue,
                "Fibonacci" to purple,
                "Factorial" to orange,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 312.dp)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 4.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(templates) { p ->
                    TemplateItem(
                        color = colorsMap[p.caption] ?: pink,
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
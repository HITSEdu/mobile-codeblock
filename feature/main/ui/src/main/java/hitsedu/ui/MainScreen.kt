package hitsedu.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hitsedu.ui.components.AboutContent
import hitsedu.ui.components.BottomSection
import hitsedu.ui.components.DocumentationContent
import hitsedu.ui.components.TopInfoSection
import hitsedu.ui_kit.Destinations
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.Header
import hitsedu.ui_kit.components.ProjectItem
import hitsedu.ui_kit.models.ProjectType
import hitsedu.ui_kit.models.ProjectUI
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavHostController,
) {
//    val viewModel = MainViewModel()
    MainScreenUI(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenUI(
//    viewModel: MainViewModel,
    navController: NavHostController,
) {
    val projects = listOf(
        ProjectUI("Сортировка пузырьком", ProjectType.BOARD),
        ProjectUI("Поиск в ширину", ProjectType.SCRIPT),
        ProjectUI("Сортировка пузырьком", ProjectType.BOARD),
        ProjectUI("Быстрая сортировка", ProjectType.SCRIPT),
        ProjectUI("Дерево решений", ProjectType.BOARD),
        ProjectUI("Поиск в ширину", ProjectType.SCRIPT),
        ProjectUI("Сортировка пузырьком", ProjectType.BOARD),
    )

    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var topSectionClick = TopSectionClick.ABOUT

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
            TopInfoSection(
                onHelpClick = {
                    scope.launch {
                        topSectionClick = TopSectionClick.DOCUMENTATION
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }
                },
                onInfoClick = {
                    scope.launch {
                        topSectionClick = TopSectionClick.ABOUT
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }
                },
            )
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
                    text = stringResource(hitsedu.ui_kit.R.string.projects),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                )
                IconButton(
                    onClick = {
//                        TODO("Filter")
                    }
                ) {
                    Icon(
                        painter = painterResource(hitsedu.ui_kit.R.drawable.icon_filter),
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
                items(projects) { p ->
                    ProjectItem(
                        p.caption,
                        p.type,
                    )
                }
            }
            BottomSection(
                onBoardClick = {
                    navController.navigate(Destinations.BOARD_SCREEN)
                },
                onScriptClick = {

                },
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
            when (topSectionClick) {
                TopSectionClick.DOCUMENTATION -> DocumentationContent()
                TopSectionClick.ABOUT -> AboutContent()
            }
        }
    }
}
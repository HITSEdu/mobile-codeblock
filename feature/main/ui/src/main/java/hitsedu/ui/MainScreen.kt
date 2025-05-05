package hitsedu.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.BottomSheet
import hitsedu.ui_kit.components.ButtonCreate
import hitsedu.ui_kit.components.ButtonInfo
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
    var text: Int = hitsedu.ui_kit.R.string.board_description

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
                        text = hitsedu.ui_kit.R.string.documentation_description
                        isBottomSheetVisible = true
                        sheetState.expand()
                    }
                },
                onInfoClick = {
                    scope.launch {
                        text = hitsedu.ui_kit.R.string.board_description
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
                        TODO("Filter")
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
            BottomSection()
        }
        BottomSheet(
            isBottomSheetVisible = isBottomSheetVisible,
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { isBottomSheetVisible = false }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 12.dp,
                        top = 8.dp,
                        end = 12.dp,
                        bottom = 24.dp,
                    ),
                contentAlignment = Alignment.TopCenter,
            ) {
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun TopInfoSection(
    onHelpClick: () -> Unit,
    onInfoClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ButtonInfo(
            icon = hitsedu.ui_kit.R.drawable.icon_help,
            text = hitsedu.ui_kit.R.string.documentation,
            onClick = { onHelpClick() }
        )
        ButtonInfo(
            icon = hitsedu.ui_kit.R.drawable.icon_info,
            text = hitsedu.ui_kit.R.string.about,
            onClick = { onInfoClick() }
        )
    }
}

@Composable
fun BottomSection() {
    BottomContainer {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(hitsedu.ui_kit.R.string.create),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
            )
            IconButton(
                onClick = {
//                    TODO("Navigate to settings")
                }
            ) {
                Icon(
                    painter = painterResource(hitsedu.ui_kit.R.drawable.icon_settings),
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ButtonCreate(
                icon = hitsedu.ui_kit.R.drawable.icon_board,
                text = hitsedu.ui_kit.R.string.board,
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    Log.i("Button Create", "board")
                }
            )
            ButtonCreate(
                icon = hitsedu.ui_kit.R.drawable.icon_script,
                text = hitsedu.ui_kit.R.string.script,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    Log.i("Button Create", "script")
                }
            )
        }
    }
}
package hitsedu.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.feature.main.ui.R
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.ButtonCreate
import hitsedu.ui_kit.components.ButtonInfo
import hitsedu.ui_kit.components.Header

@Composable
fun MainScreen() {
//    val viewModel = MainViewModel()
    MainScreenUI()
}

@Composable
private fun MainScreenUI(
//    viewModel: MainViewModel,
) {
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
                        start = 24.dp,
                        top = 12.dp,
                        end = 24.dp,
                        bottom = 32.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ButtonInfo(
                    icon = hitsedu.ui_kit.R.drawable.icon_help,
                    text = hitsedu.ui_kit.R.string.documentation,
                    onClick = {
                        Log.i("MainScreen", "click docs")
                    }
                )
                ButtonInfo(
                    icon = hitsedu.ui_kit.R.drawable.icon_info,
                    text = hitsedu.ui_kit.R.string.about,
                    onClick = {
                        Log.i("MainScreen", "click about")
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp,
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
                    onClick = {TODO("Filter")}
                ) {
                    Icon(
                        painter = painterResource(hitsedu.ui_kit.R.drawable.icon_filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
//            LazyColumn(
//                modifier = Modifier
//            ) {  }
            Spacer(modifier = Modifier.weight(1f))
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
                        onClick = {TODO("Navigate to settings")}
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
    }
}
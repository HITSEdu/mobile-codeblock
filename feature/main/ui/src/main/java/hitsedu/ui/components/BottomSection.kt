package hitsedu.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.components.BottomContainer
import hitsedu.ui_kit.components.ButtonCreate
import hitsedu.ui_kit.components.ButtonInfo

@Composable
fun BottomSection(
    onBoardClick: () -> Unit,
    onTemplatesClick: () -> Unit,
    onInfoClick: () -> Unit,
) {
    BottomContainer {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(hitsedu.ui_kit.R.string.create),
                style = MaterialTheme.typography.titleSmall,
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
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.weight(1f),
            ) {
                ButtonCreate(
                    icon = hitsedu.ui_kit.R.drawable.icon_board,
                    text = hitsedu.ui_kit.R.string.board,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    onClick = onBoardClick
                )
            }
            Box(
                modifier = Modifier.weight(1f),
            ) {
                ButtonCreate(
                    icon = hitsedu.ui_kit.R.drawable.icon_script,
                    text = hitsedu.ui_kit.R.string.templates,
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    onClick = onTemplatesClick
                )
            }
        }
        ButtonInfo(
            icon = hitsedu.ui_kit.R.drawable.icon_help,
            text = hitsedu.ui_kit.R.string.documentation,
            onClick = onInfoClick,
        )
    }
}
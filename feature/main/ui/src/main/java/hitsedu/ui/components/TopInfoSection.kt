package hitsedu.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.components.ButtonInfo

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
            onClick = onHelpClick,
        )
        ButtonInfo(
            icon = hitsedu.ui_kit.R.drawable.icon_info,
            text = hitsedu.ui_kit.R.string.about,
            onClick = onInfoClick,
        )
    }
}
package hitsedu.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import hitsedu.ui_kit.R
import hitsedu.ui_kit.theme.border
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.shapeMedium
import hitsedu.ui_kit.theme.size24
import hitsedu.ui_kit.theme.size32
import hitsedu.ui_kit.theme.size56
import hitsedu.ui_kit.theme.spaceLarge

@Composable
fun ProjectItem(
    caption: String,
    onNavigate: () -> Unit,
    onDelete: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(size56)
            .clickable {
                onNavigate()
            },
        shape = RoundedCornerShape(shapeMedium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(
            width = border,
            color = MaterialTheme.colorScheme.onPrimary.copy(0.3f),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingLarge),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(spaceLarge),
        ) {
            Box(
                modifier = Modifier
                    .size(size32)
                    .clip(RoundedCornerShape(shapeMedium)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_board),
                    contentDescription = "Project item",
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Text(
                text = caption,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f),
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .size(size32)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_delete),
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(size24)
                        .fillMaxSize()
                        .align(Alignment.Bottom),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
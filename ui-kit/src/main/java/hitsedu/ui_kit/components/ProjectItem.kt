package hitsedu.ui_kit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ProjectType

@Composable
fun ProjectItem(
    caption: String,
    type: ProjectType,
    // navController
) {
    val containerColor = when (type) {
        ProjectType.BOARD -> MaterialTheme.colorScheme.primary
        ProjectType.SCRIPT -> MaterialTheme.colorScheme.secondary
    }
    val icon = when (type) {
        ProjectType.BOARD -> R.drawable.icon_board
        ProjectType.SCRIPT -> R.drawable.icon_script
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable {
//                TODO("Navigate to project detail")
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onPrimary.copy(0.3f),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.onPrimary.copy(0.8f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "Type",
                    modifier = Modifier
                        .fillMaxSize(),
                    tint = containerColor,
                )
            }
            Text(
                text = caption,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
//                    TODO("Edit")
                },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_edit),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(24.dp)
                        .fillMaxSize()
                        .align(Alignment.Bottom),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
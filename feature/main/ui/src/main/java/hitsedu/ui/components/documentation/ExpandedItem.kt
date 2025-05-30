package hitsedu.ui.components.documentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hitsedu.ui_kit.theme.paddingMedium
import hitsedu.ui_kit.theme.size24
import hitsedu.ui_kit.theme.spaceSmall

@Composable
fun ExpandedItem(
    title: Int,
    content: @Composable () -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 270f else 180f,
        label = "Icon Rotation"
    )

    Column(
        modifier = Modifier
            .clickable { isExpanded = !isExpanded }
            .background(
                MaterialTheme.colorScheme.primary
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spaceSmall),
        ) {
            Icon(
                painter = painterResource(hitsedu.ui_kit.R.drawable.icon_left),
                contentDescription = "Arrow",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(size24)
                    .graphicsLayer {
                        rotationZ = rotation
                    },
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        AnimatedVisibility(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                )
                .fillMaxWidth(),
            visible = isExpanded,
        ) {
            content()
        }
    }
}

package hitsedu.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import hitsedu.ui_kit.R
import hitsedu.ui_kit.theme.border
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.shapeMedium
import hitsedu.ui_kit.theme.size12
import hitsedu.ui_kit.theme.size32
import hitsedu.ui_kit.theme.size56

@Composable
fun TemplateItem(
    color: Color,
    caption: String,
    onNavigate: () -> Unit,
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
            color = color,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingLarge),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_board),
                contentDescription = "Board",
                modifier = Modifier
                    .size(size32),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.width(size12))
            Text(
                text = caption,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}
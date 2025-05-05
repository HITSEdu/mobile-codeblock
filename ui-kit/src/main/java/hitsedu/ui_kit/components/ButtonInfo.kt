package hitsedu.ui_kit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ButtonInfo(
    icon: Int,
    text: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(116.dp)
            .width(172.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = MaterialTheme.colorScheme.onPrimary,
            ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Info",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}
package hitsedu.ui_kit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCreate(
    icon: Int,
    text: Int,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(116.dp)
            .width(172.dp),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = backgroundColor,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onPrimary.copy(0.3f),
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Info",
                modifier = Modifier
                    .size(48.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}
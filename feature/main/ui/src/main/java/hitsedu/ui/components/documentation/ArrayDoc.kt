package hitsedu.ui.components.documentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.components.MockBox
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.darkPrimary

@Composable
fun ArrayDoc(
    name: String,
    values: List<String>,
) {
    MockBox(
        backgroundColor = blue,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = "{",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        values.forEach {
            Box(
                modifier = Modifier
                    .size(32.dp, 16.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(16.dp),
                    )
                    .padding(horizontal = 4.dp),
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
        Text(
            text = "}",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
    }
}
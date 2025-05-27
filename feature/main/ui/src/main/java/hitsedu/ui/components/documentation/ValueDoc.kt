package hitsedu.ui.components.documentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.components.MockBox

@Composable
fun ValueDoc(
    value: String,
) {
    Box(
        modifier = Modifier
            .offset(y = (-4).dp),
    ) {
        MockBox(
            backgroundColor = MaterialTheme.colorScheme.primary,
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
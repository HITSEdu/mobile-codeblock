package hitsedu.board.ui.components.elements.value

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.components.MockBox

@Composable
fun ValueMock() {
    Box(
        modifier = Modifier
            .offset(y = (-4).dp),
    ) {
        MockBox(
            backgroundColor = MaterialTheme.colorScheme.secondary,
        ) {
            Text(
                text = "10 + 3 - 8",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
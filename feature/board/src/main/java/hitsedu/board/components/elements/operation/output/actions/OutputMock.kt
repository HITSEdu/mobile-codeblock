package hitsedu.board.components.elements.operation.output.actions

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
import hitsedu.board.components.MockBox
import hitsedu.ui_kit.theme.purple

@Composable
fun OutputMock() {
    MockBox(
        backgroundColor = purple,
    ) {
        Text(
            text = "Print: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Box(
            modifier = Modifier
                .size(92.dp, 16.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(16.dp),
                )
                .padding(horizontal = 4.dp),
        ) {
            Text(
                text = "\"Hello!\"",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
package hitsedu.board.components.elements.operation.variable.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.components.MockBox
import hitsedu.ui_kit.theme.orange

@Composable
fun VariableMock() {
    MockBox(
        backgroundColor = orange,
    ) {
        Text(
            text = "variable",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(16.dp),
                )
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "value",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

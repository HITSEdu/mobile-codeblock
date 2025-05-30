package hitsedu.ui.components.documentation

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
import hitsedu.board.ui.components.MockBox
import hitsedu.ui_kit.theme.orange
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.shapeLarge

@Composable
fun VariableDoc(
    name: String,
    value: String,
) {
    MockBox(
        backgroundColor = orange,
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
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(shapeLarge),
                )
                .padding(horizontal = paddingSmall),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

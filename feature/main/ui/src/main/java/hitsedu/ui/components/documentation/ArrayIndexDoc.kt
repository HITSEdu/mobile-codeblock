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
import hitsedu.board.components.MockBox
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.shapeLarge
import hitsedu.ui_kit.theme.size16
import hitsedu.ui_kit.theme.size32

@Composable
fun ArrayIndexDoc(
    name: String,
    index: String,
    value: String,
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
            text = "[",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Box(
            modifier = Modifier
                .size(size32, size16)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(shapeLarge),
                )
                .padding(horizontal = paddingSmall),
        ) {
            Text(
                text = index,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Text(
            text = "]",
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
                .size(size32, size16)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(shapeLarge),
                )
                .padding(horizontal = paddingSmall),
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
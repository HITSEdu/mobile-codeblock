package hitsedu.board.components.elements.operation.condition.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hitsedu.board.components.MockBox
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.shapeLarge
import hitsedu.ui_kit.theme.size12
import hitsedu.ui_kit.theme.size192
import hitsedu.ui_kit.theme.yellow

@Composable
fun ConditionElseMock() {
    MockBox(
        backgroundColor = yellow,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(paddingSmall),
        ) {
            Text(
                text = "Else",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Box(
                modifier = Modifier
                    .size(size192, size12)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(shapeLarge),
                    )
                    .padding(horizontal = paddingSmall),
            )
        }
    }
}
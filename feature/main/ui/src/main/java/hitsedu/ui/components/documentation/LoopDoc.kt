package hitsedu.ui.components.documentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hitsedu.board.components.MockBox
import hitsedu.ui_kit.theme.green
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.shapeLarge
import hitsedu.ui_kit.theme.size156
import hitsedu.ui_kit.theme.size16
import hitsedu.ui_kit.theme.size192
import hitsedu.ui_kit.theme.size8
import hitsedu.ui_kit.theme.size92
import hitsedu.ui_kit.theme.spaceSmall

@Composable
fun LoopDoc(
    start: String,
    stop: String,
    step: String,
) {
    MockBox(
        backgroundColor = green,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(spaceSmall),
        ) {
            Row(
                modifier = Modifier
                    .size(size192, size16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(hitsedu.ui_kit.R.string.for_statement),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(modifier = Modifier.height(size8))
                Box(
                    modifier = Modifier
                        .size(size156, size16)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(shapeLarge),
                        )
                        .padding(horizontal = paddingSmall),
                ) {
                    Text(
                        text = start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
            Row(
                modifier = Modifier
                    .size(size192, size16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .size(size92, size16)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(shapeLarge),
                        )
                        .padding(horizontal = paddingSmall),
                ) {
                    Text(
                        text = stop,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(size92, size16)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(shapeLarge),
                        )
                        .padding(horizontal = paddingSmall),
                ) {
                    Text(
                        text = step,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(size192, size16)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(shapeLarge),
                    )
                    .padding(horizontal = paddingSmall),
            )
        }
    }
}
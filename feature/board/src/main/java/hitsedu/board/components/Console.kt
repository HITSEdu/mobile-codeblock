package hitsedu.board.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.models.exception.ConsoleOutputUIO
import hitsedu.ui_kit.theme.red

@Composable
fun Console(
    consoleOutput: List<ConsoleOutputUIO>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                max = 384.dp,
                min = 256.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp,
        )
    ) {
        if (consoleOutput.isNotEmpty()) {
            items(consoleOutput) { out ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = ">",
                        style = MaterialTheme.typography.titleSmall,
                        color = red,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = out.output,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        } else {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = ">",
                        style = MaterialTheme.typography.titleSmall,
                        color = red,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(hitsedu.ui_kit.R.string.empty_console),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
    }
}
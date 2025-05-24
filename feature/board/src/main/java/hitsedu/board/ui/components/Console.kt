package hitsedu.board.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.theme.red

@Composable
fun Console(

) {
    /*
    ConsoleOutput(
        val output: String,
        val error: Error?
    )
    Error(
        val blockId: Long,
        val message: String,
    )
    **/

    val color = if (true) MaterialTheme.colorScheme.onPrimary else red
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
        items(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "C:\\Users\\HITSEdu> ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = "output: 1",
                    style = MaterialTheme.typography.bodyMedium,
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
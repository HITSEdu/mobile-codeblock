package hitsedu.board.components.elements.value

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.BoardViewModel
import hitsedu.board.components.dnd.DropHere
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.theme.red

@Composable
fun ContainerValue(
    parent: OperationUIO,
    viewModel: BoardViewModel,
) {
    DropHere(ValueUIO::class) { isInBound, value ->
        if (value is ValueUIO) {
            LaunchedEffect(key1 = value) {
                viewModel.addValue(parent, value)
            }
        }
        val text = if (isInBound) R.string.drop else R.string.drag_value
        val color = if (isInBound) red else MaterialTheme.colorScheme.onPrimary
        Box(
            modifier = Modifier
                .size(72.dp, 24.dp)
                .border(
                    1.dp,
                    color,
                    RoundedCornerShape(12.dp),
                )
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 6.dp,
                    vertical = 6.dp,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
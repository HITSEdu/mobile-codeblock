package hitsedu.board.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
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
import hitsedu.ui_kit.models.operation.Operation
import hitsedu.ui_kit.models.operation.OperationValue
import hitsedu.ui_kit.theme.red

@Composable
fun ScopeContainerValue(
    parent: Operation,
    viewModel: BoardViewModel,
) {
    DropHere<OperationValue> { isInBound, operation ->
        if (operation is OperationValue) {
            LaunchedEffect(key1 = operation) {
                viewModel.addValue(operation, parent)
            }
        }
        val text =
            if (isInBound) hitsedu.ui_kit.R.string.drop else hitsedu.ui_kit.R.string.drag_value
        val color = if (isInBound) red else MaterialTheme.colorScheme.primary
        Box(
            modifier = Modifier
                .widthIn(92.dp, 128.dp)
                .wrapContentHeight()
                .border(
                    2.dp,
                    color,
                    RoundedCornerShape(16.dp),
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
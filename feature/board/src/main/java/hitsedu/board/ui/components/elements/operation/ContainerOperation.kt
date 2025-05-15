package hitsedu.board.ui.components.elements.operation

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
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.dnd.DropHere
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.theme.red

@Composable
fun ContainerOperation(
    viewModel: BoardViewModel,
) {
    DropHere(OperationUIO::class) { isInBound, operation ->
        if (operation != null) {
            LaunchedEffect(key1 = operation) {
                viewModel.addVariable(operation)
            }
        }
        val text = if (isInBound) hitsedu.ui_kit.R.string.drop else hitsedu.ui_kit.R.string.drag_operation
        val color = if (isInBound) red else MaterialTheme.colorScheme.onPrimary
        Box(
            modifier = Modifier
                .size(156.dp, 32.dp)
                .border(
                    2.dp,
                    color,
                    RoundedCornerShape(12.dp),
                )
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
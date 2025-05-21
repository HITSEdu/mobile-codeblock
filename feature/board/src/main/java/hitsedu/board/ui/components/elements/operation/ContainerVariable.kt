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
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.red

@Composable
fun ContainerVariable(
    parentScope: ScopeUIO,
    parentOperation: OperationUIO,
    viewModel: BoardViewModel,
) {
    DropHere(OperationVariableUIO::class) { isInBound, operation ->
        if (operation != null) {
            LaunchedEffect(key1 = operation) {

            }
        }
        val text = if (isInBound) R.string.drop else R.string.variable
        val color = if (isInBound) red else MaterialTheme.colorScheme.onPrimary
        Box(
            modifier = Modifier
                .size(122.dp, 24.dp)
                .border(
                    1.dp,
                    color,
                    RoundedCornerShape(16.dp),
                )
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(16.dp)
                )
                .padding(
                    horizontal = 12.dp,
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
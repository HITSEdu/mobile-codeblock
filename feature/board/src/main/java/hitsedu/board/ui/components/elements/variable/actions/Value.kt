package hitsedu.board.ui.components.elements.variable.actions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.OperationContainer
import hitsedu.ui_kit.models.operation.OperationUIOValue

@Composable
fun Value(
    value: OperationUIOValue,
    viewModel: BoardViewModel? = null,
    onDeleteClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .offset(y = (-4).dp),
    ) {
        OperationContainer(
            operationUIO = value,
            viewModel = viewModel,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            onDeleteClick = onDeleteClick,
        ) {
            Text(
                text = value.values,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
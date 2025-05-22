package hitsedu.board.ui.components.elements.value

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.theme.darkPrimary

@Composable
fun Value(
    parent: OperationUIO,
    value: ValueUIO,
    viewModel: BoardViewModel,
    onDeleteClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .offset(y = (-4).dp),
    ) {
        ValueBox(
            parent = parent,
            value = value,
            viewModel = viewModel,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            onDeleteClick = onDeleteClick,
        ) {
            Text(
                text = value.value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
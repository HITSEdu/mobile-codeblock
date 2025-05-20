package hitsedu.board.ui.components.elements.operation.array

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.dnd.DraggableItem
import hitsedu.board.ui.components.elements.operation.array.actions.ArrayIndexMock
import hitsedu.board.ui.components.elements.operation.array.actions.ArrayMock
import hitsedu.board.ui.utils.Default

@Composable
fun ArrayContent(
    viewModel: BoardViewModel,
    onDragStart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(hitsedu.ui_kit.R.string.array),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        DraggableItem(
            dataToDrop = Default.array,
            viewModel = viewModel,
            onDragStart = onDragStart,
        ) {
            ArrayMock()
        }
        Text(
            text = stringResource(hitsedu.ui_kit.R.string.array_index),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        DraggableItem(
            dataToDrop = Default.arrayIndex,
            viewModel = viewModel,
            onDragStart = onDragStart,
        ) {
            ArrayIndexMock()
        }
    }
}
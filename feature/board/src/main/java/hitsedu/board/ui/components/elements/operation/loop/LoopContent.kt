package hitsedu.board.ui.components.elements.operation.loop

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
import hitsedu.board.ui.components.elements.operation.loop.actions.LoopMock
import hitsedu.board.ui.utils.Default
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.spaceSmall

@Composable
fun LoopContent(
    viewModel: BoardViewModel,
    onDragStart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = paddingSmall),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(spaceSmall),
    ) {
        Text(
            text = stringResource(hitsedu.ui_kit.R.string.loop),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        DraggableItem(
            dataToDrop = Default.loop,
            viewModel = viewModel,
            onDragStart = onDragStart,
        ) {
            LoopMock()
        }
    }
}
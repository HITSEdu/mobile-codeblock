package hitsedu.board.ui.components.elements.condition

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
import hitsedu.board.ui.components.elements.condition.actions.ConditionIfMock
import hitsedu.board.ui.utils.Default

@Composable
fun ConditionContent(
    viewModel: BoardViewModel,
    onDragStart: () -> Unit,
    onDragStop: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(hitsedu.ui_kit.R.string.if_statement),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        DraggableItem(
            dataToDrop = Default.condition,
            viewModel = viewModel,
            onDragStart = onDragStart,
            onDragStop = onDragStop,
        ) {
            ConditionIfMock()
        }
    }
}
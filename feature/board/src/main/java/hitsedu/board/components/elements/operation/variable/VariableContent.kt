package hitsedu.board.components.elements.operation.variable

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
import hitsedu.board.BoardViewModel
import hitsedu.board.components.dnd.DraggableItem
import hitsedu.board.components.elements.operation.variable.actions.VariableMock
import hitsedu.board.utils.Default
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.spaceSmall

@Composable
fun VariableContent(
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
            text = stringResource(hitsedu.ui_kit.R.string.variable_declare),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        DraggableItem(
            dataToDrop = Default.variable,
            viewModel = viewModel,
            onDragStart = onDragStart,
        ) {
            VariableMock()
        }
    }
}
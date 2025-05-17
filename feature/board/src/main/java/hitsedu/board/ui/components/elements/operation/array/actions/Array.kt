package hitsedu.board.ui.components.elements.operation.array.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.value.Value
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.darkPrimary

@Composable
fun Array(
    parentScope: ScopeUIO,
    array: OperationArrayUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        operationUIO = array,
        viewModel = viewModel,
        backgroundColor = blue,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, array)
        },
    ) {
        Text(
            text = array.name,
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            array.values.forEach {
                Value(
                    value = it,
                    viewModel = viewModel,
                    onDeleteClick = {

                    },
                )
            }
            ContainerValue(
                parent = array,
                viewModel = viewModel,
            )
        }
    }
}
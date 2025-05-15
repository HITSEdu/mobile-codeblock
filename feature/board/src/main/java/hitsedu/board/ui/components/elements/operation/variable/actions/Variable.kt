package hitsedu.board.ui.components.elements.operation.variable.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.Value
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.orange

@Composable
fun Variable(
    variable: OperationVariableUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        operationUIO = variable,
        viewModel = viewModel,
        backgroundColor = orange,
        onDeleteClick = { viewModel.removeVariable(variable) },
    ) {
        Text(
            text = variable.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        if (variable.value.value.isBlank())
            ContainerValue(
                parent = variable,
                viewModel = viewModel,
            )
        else
            Value(
                value = variable.value,
                viewModel = viewModel,
                onDeleteClick = { viewModel.removeValue(variable.value) },
            )
    }
}
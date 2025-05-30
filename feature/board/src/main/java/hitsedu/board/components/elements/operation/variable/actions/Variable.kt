package hitsedu.board.components.elements.operation.variable.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.BoardViewModel
import hitsedu.board.components.elements.operation.OperationBox
import hitsedu.board.components.elements.value.ContainerValue
import hitsedu.board.components.elements.value.Value
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.darkPrimary
import hitsedu.ui_kit.theme.orange

@Composable
fun Variable(
    parentScope: ScopeUIO,
    variable: OperationVariableUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = variable,
        viewModel = viewModel,
        backgroundColor = orange,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, variable)
        },
    ) {
        Text(
            text = variable.name,
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        if (variable.value.value.isBlank())
            ContainerValue(
                parent = variable,
                viewModel = viewModel,
            )
        else
            Value(
                parent = variable,
                value = variable.value,
                viewModel = viewModel,
                onDeleteClick = {
                    viewModel.removeValue(
                        variable,
                        variable.value
                    )
                },
            )
    }
}
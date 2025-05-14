package hitsedu.board.ui.components.elements.variable.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.ScopeContainerValue
import hitsedu.board.ui.components.elements.OperationContainer
import hitsedu.ui_kit.models.operation.OperationVariable
import hitsedu.ui_kit.theme.orange

@Composable
fun Variable(
    variable: OperationVariable,
    viewModel: BoardViewModel,
) {
    OperationContainer(
        operation = variable,
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
        if (variable.value.values.isBlank())
            ScopeContainerValue(
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
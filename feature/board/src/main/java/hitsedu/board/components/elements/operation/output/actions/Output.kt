package hitsedu.board.components.elements.operation.output.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.BoardViewModel
import hitsedu.board.components.elements.operation.OperationBox
import hitsedu.board.components.elements.value.ContainerValue
import hitsedu.board.components.elements.value.Value
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.theme.purple

@Composable
fun Output(
    parentScope: ScopeUIO,
    output: OperationOutputUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = output,
        viewModel = viewModel,
        backgroundColor = purple,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, output)
        },
    ) {
        Text(
            text = "Print: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        if (output.value.value.isBlank())
            ContainerValue(
                parent = output,
                viewModel = viewModel,
            )
        else {
            Value(
                parent = output,
                value = output.value,
                viewModel = viewModel,
                onDeleteClick = {
                    viewModel.removeValue(output, output.value)
                }
            )
        }
    }
}
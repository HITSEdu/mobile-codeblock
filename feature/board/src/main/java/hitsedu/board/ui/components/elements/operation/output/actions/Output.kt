package hitsedu.board.ui.components.elements.operation.output.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.value.Value
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
        operationUIO = output,
        viewModel = viewModel,
        backgroundColor = purple,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, output)
        },
    ) {
        Text(
            text = "Print: ",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        if (output.value.value.isBlank())
            ContainerValue(
                parent = output,
                viewModel = viewModel,
            )
        else {
            Value(
                value = output.value,
                viewModel = viewModel,
                onDeleteClick = {
                    // TODO("viewModel delete value")
                }
            )
        }
    }
}
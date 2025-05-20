package hitsedu.board.ui.components.elements.operation.array.actions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.value.Value
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.darkPrimary

@Composable
fun ArrayIndex(
    parentScope: ScopeUIO,
    arrayIndex: OperationArrayIndexUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = arrayIndex,
        viewModel = viewModel,
        backgroundColor = blue,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, arrayIndex)
        },
    ) {
        Text(
            text = arrayIndex.name,
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "[",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        if (arrayIndex.index.value.isBlank())
            ContainerValue(
                parent = arrayIndex,
                viewModel = viewModel,
            )
        else
            Value(
                parent = arrayIndex,
                value = arrayIndex.index,
                viewModel = viewModel,
                onDeleteClick = {
                    viewModel.removeValue(
                        arrayIndex,
                        arrayIndex.index
                    )
                },
            )
        Text(
            text = "]",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        if (arrayIndex.value.value.isBlank())
            ContainerValue(
                parent = arrayIndex,
                viewModel = viewModel,
            )
        else
            Value(
                parent = arrayIndex,
                value = arrayIndex.value,
                viewModel = viewModel,
                onDeleteClick = {
                    viewModel.removeValue(
                        arrayIndex,
                        arrayIndex.value,
                    )
                },
            )
    }
}
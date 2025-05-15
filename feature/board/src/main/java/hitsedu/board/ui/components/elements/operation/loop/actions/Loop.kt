package hitsedu.board.ui.components.elements.operation.loop.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.ContainerOperation
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.value.Value
import hitsedu.board.ui.utils.RenderOperation
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.theme.green

@Composable
fun Loop(
    loop: OperationForUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        operationUIO = loop,
        viewModel = viewModel,
        backgroundColor = green,
        onDeleteClick = {
            viewModel.removeVariable(loop)
        },
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.for_statement),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Start),
            )
            if (loop.value.value.isBlank())
                ContainerValue(
                    parent = loop,
                    viewModel = viewModel,
                )
            else
                Value(
                    value = loop.value,
                    viewModel = viewModel,
                    onDeleteClick = { viewModel.removeValue(loop.value) },
                )
            if (loop.condition.value.isBlank())
                ContainerValue(
                    parent = loop,
                    viewModel = viewModel,
                )
            else
                Value(
                    value = loop.value,
                    viewModel = viewModel,
                    onDeleteClick = {},
                )
            if (loop.scope.operationUIOS.isEmpty())
                ContainerOperation(
                    viewModel = viewModel,
                )
            else
                loop.scope.operationUIOS.forEach { it.RenderOperation(viewModel) }
        }
    }
}
package hitsedu.board.components.elements.operation.loop.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.BoardViewModel
import hitsedu.board.components.elements.operation.ContainerOperation
import hitsedu.board.components.elements.operation.OperationBox
import hitsedu.board.components.elements.value.ContainerValue
import hitsedu.board.components.elements.value.Value
import hitsedu.board.utils.RenderOperation
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.theme.darkPrimary
import hitsedu.ui_kit.theme.green

@Composable
fun Loop(
    parentScope: ScopeUIO,
    loop: OperationForUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = loop,
        viewModel = viewModel,
        backgroundColor = green,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, loop)
        },
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Text(
                    text = stringResource(R.string.for_statement),
                    style = MaterialTheme.typography.titleSmall,
                    color = darkPrimary,
                )
                if (loop.variable.value.isBlank())
                    ContainerValue(
                        parent = loop,
                        viewModel = viewModel,
                    )
                else
                    Value(
                        parent = loop,
                        value = loop.variable,
                        viewModel = viewModel,
                        onDeleteClick = {
                            viewModel.removeValue(loop, loop.variable)
                        },
                    )
            }
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (loop.condition.value.isBlank())
                    ContainerValue(
                        parent = loop,
                        viewModel = viewModel,
                    )
                else
                    Value(
                        parent = loop,
                        value = loop.condition,
                        viewModel = viewModel,
                        onDeleteClick = {
                            viewModel.removeValue(loop, loop.condition)
                        },
                    )
                if (loop.value.value.isBlank())
                    ContainerValue(
                        parent = loop,
                        viewModel = viewModel,
                    )
                else
                    Value(
                        parent = loop,
                        value = loop.value,
                        viewModel = viewModel,
                        onDeleteClick = {
                            viewModel.removeValue(loop, loop.value)
                        },
                    )
            }
            if (loop.scope.operationUIOS.isEmpty())
                ContainerOperation(
                    parentScope = loop.scope,
                    viewModel = viewModel,
                )
            else {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    loop.scope.operationUIOS.forEach { it.RenderOperation(loop.scope, viewModel) }
                    Spacer(modifier = Modifier.height(2.dp))
                    ContainerOperation(
                        parentScope = loop.scope,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
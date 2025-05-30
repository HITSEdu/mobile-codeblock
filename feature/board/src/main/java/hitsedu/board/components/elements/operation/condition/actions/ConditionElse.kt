package hitsedu.board.components.elements.operation.condition.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import hitsedu.board.utils.RenderOperation
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.theme.darkPrimary
import hitsedu.ui_kit.theme.yellow

@Composable
fun ConditionElse(
    parentScope: ScopeUIO,
    conditionElse: OperationElseUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = conditionElse,
        viewModel = viewModel,
        backgroundColor = yellow,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, conditionElse)
        },
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(R.string.condition_else),
                style = MaterialTheme.typography.titleSmall,
                color = darkPrimary,
            )
            if (conditionElse.scope.operationUIOS.isEmpty())
                ContainerOperation(
                    parentScope = conditionElse.scope,
                    viewModel = viewModel,
                )
            else {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    conditionElse.scope.operationUIOS.forEach {
                        it.RenderOperation(conditionElse.scope, viewModel)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    ContainerOperation(
                        parentScope = conditionElse.scope,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
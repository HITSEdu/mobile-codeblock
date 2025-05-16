package hitsedu.board.ui.components.elements.operation.condition.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.theme.darkPrimary
import hitsedu.ui_kit.theme.yellow

@Composable
fun ConditionIf(
    parentScope: ScopeUIO,
    conditionIf: OperationIfUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        operationUIO = conditionIf,
        viewModel = viewModel,
        backgroundColor = yellow,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, conditionIf)
        },
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = stringResource(R.string.condition_if),
                    style = MaterialTheme.typography.titleSmall,
                    color = darkPrimary,
                    modifier = Modifier.padding(end = 24.dp)
                )
                if (conditionIf.value.value.isBlank())
                    ContainerValue(
                        parent = conditionIf,
                        viewModel = viewModel,
                    )
                else
                    Value(
                        value = conditionIf.value,
                        viewModel = viewModel,
                        onDeleteClick = {

                        },
                    )
            }
            if (conditionIf.scope.operationUIOS.isEmpty())
                ContainerOperation(
                    parentScope = conditionIf.scope,
                    viewModel = viewModel,
                )
            else {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    conditionIf.scope.operationUIOS.forEach {
                        it.RenderOperation(
                            conditionIf.scope,
                            viewModel
                        )
                    }
                    ContainerOperation(
                        parentScope = conditionIf.scope,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
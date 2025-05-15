package hitsedu.board.ui.utils

import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.condition.actions.ConditionElse
import hitsedu.board.ui.components.elements.operation.condition.actions.ConditionIf
import hitsedu.board.ui.components.elements.operation.loop.actions.Loop
import hitsedu.board.ui.components.elements.operation.variable.actions.Variable
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

@Composable
fun OperationUIO.RenderOperation(viewModel: BoardViewModel) {
    when (this) {
        is OperationVariableUIO -> Variable(variable = this, viewModel = viewModel)
        is OperationIfUIO -> ConditionIf(conditionIf = this, viewModel = viewModel)
        is OperationElseUIO -> ConditionElse(conditionElse = this, viewModel = viewModel)
        is OperationForUIO -> Loop(loop = this, viewModel = viewModel)
        is OperationArrayUIO -> {}
    }
}
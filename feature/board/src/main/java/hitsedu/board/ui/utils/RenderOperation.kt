package hitsedu.board.ui.utils

import androidx.compose.runtime.Composable
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.array.actions.Array
import hitsedu.board.ui.components.elements.operation.array.actions.ArrayIndex
import hitsedu.board.ui.components.elements.operation.array.actions.ArrayIndexMock
import hitsedu.board.ui.components.elements.operation.condition.actions.ConditionElse
import hitsedu.board.ui.components.elements.operation.condition.actions.ConditionIf
import hitsedu.board.ui.components.elements.operation.loop.actions.Loop
import hitsedu.board.ui.components.elements.operation.output.actions.Output
import hitsedu.board.ui.components.elements.operation.variable.actions.Variable
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

@Composable
fun OperationUIO.RenderOperation(parentScope: ScopeUIO, viewModel: BoardViewModel) {
    when (this) {
        is OperationVariableUIO -> Variable(
            parentScope = parentScope,
            variable = this,
            viewModel = viewModel,
        )

        is OperationIfUIO -> ConditionIf(
            parentScope = parentScope,
            conditionIf = this,
            viewModel = viewModel,
        )

        is OperationElseUIO -> ConditionElse(
            parentScope = parentScope,
            conditionElse = this,
            viewModel = viewModel,
        )

        is OperationForUIO -> Loop(
            parentScope = parentScope,
            loop = this,
            viewModel = viewModel,
        )

        is OperationArrayUIO -> Array(
            parentScope = parentScope,
            array = this,
            viewModel = viewModel,
        )

        is OperationOutputUIO -> Output(
            parentScope = parentScope,
            output = this,
            viewModel = viewModel,
        )

        is OperationArrayIndexUIO -> ArrayIndex(
            parentScope = parentScope,
            arrayIndex = this,
            viewModel = viewModel,
        )
    }
}
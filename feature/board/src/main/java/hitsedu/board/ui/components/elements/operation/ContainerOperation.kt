package hitsedu.board.ui.components.elements.operation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.dnd.DropHere
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.red

@Composable
fun ContainerOperation(
    parentScope: ScopeUIO,
    viewModel: BoardViewModel,
) {
    DropHere(OperationUIO::class) { isInBound, operation ->
        if (operation != null) {
            val operationWithId = when (operation) {
                is OperationVariableUIO -> operation.copy(id = viewModel.getRandom())
                is OperationArrayUIO -> operation.copy(id = viewModel.getRandom())
                is OperationArrayIndexUIO -> operation.copy(id = viewModel.getRandom())
                is OperationIfUIO -> {
                    val newScope = ScopeUIO(
                        operationUIOS = operation.scope.operationUIOS,
                        id = viewModel.getRandom()
                    )
                    operation.copy(
                        id = viewModel.getRandom(),
                        scope = newScope
                    )
                }

                is OperationElseUIO -> {
                    val newScope = ScopeUIO(
                        operationUIOS = operation.scope.operationUIOS,
                        id = viewModel.getRandom()
                    )
                    operation.copy(
                        id = viewModel.getRandom(),
                        scope = newScope
                    )
                }

                is OperationForUIO -> {
                    val newScope = ScopeUIO(
                        operationUIOS = operation.scope.operationUIOS,
                        id = viewModel.getRandom()
                    )
                    operation.copy(
                        id = viewModel.getRandom(),
                        scope = newScope
                    )
                }

                is OperationOutputUIO -> operation.copy(id = viewModel.getRandom())
            }
            LaunchedEffect(key1 = operation) {
                viewModel.addOperation(parentScope, operationWithId)
            }
        }
        val text = if (isInBound) hitsedu.ui_kit.R.string.drop else hitsedu.ui_kit.R.string.drag_operation
        val color = if (isInBound) red else MaterialTheme.colorScheme.onPrimary
        Box(
            modifier = Modifier
                .size(156.dp, 28.dp)
                .border(
                    2.dp,
                    color,
                    RoundedCornerShape(12.dp),
                )
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
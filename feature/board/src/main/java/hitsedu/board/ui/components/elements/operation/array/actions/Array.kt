package hitsedu.board.ui.components.elements.operation.array.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.OperationBox
import hitsedu.board.ui.components.elements.value.ContainerValue
import hitsedu.board.ui.components.elements.value.Value
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.theme.blue
import hitsedu.ui_kit.theme.darkPrimary

@Composable
fun Array(
    parentScope: ScopeUIO,
    array: OperationArrayUIO,
    viewModel: BoardViewModel,
) {
    OperationBox(
        parent = parentScope,
        operationUIO = array,
        viewModel = viewModel,
        backgroundColor = blue,
        onDeleteClick = {
            viewModel.removeOperation(parentScope, array)
        },
    ) {
        Text(
            text = array.name,
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "=",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        Text(
            text = "{",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .heightIn(max = 52.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(array.values) { value ->
                Value(
                    parent = array,
                    value = value,
                    viewModel = viewModel,
                    onDeleteClick = {
                        viewModel.removeValue(array, value)
                    },
                )
            }
            item {
                ContainerValue(
                    parent = array,
                    viewModel = viewModel,
                )
            }
        }
        Text(
            text = "}",
            style = MaterialTheme.typography.bodyMedium,
            color = darkPrimary,
        )
    }
}
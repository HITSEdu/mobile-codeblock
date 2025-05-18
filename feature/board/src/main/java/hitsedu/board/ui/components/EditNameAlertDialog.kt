package hitsedu.board.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.red

@Composable
fun EditNameAlertDialog(
    parent: ScopeUIO,
    operationUIO: OperationUIO,
    viewModel: BoardViewModel,
    onDismissRequest: () -> Unit,
) {
    var input by remember { mutableStateOf("") }

    LaunchedEffect(operationUIO) {
        input = when (operationUIO) {
            is OperationVariableUIO -> operationUIO.name
            is OperationArrayUIO -> operationUIO.name
            else -> ""
        }
    }

    AlertDialog(
        title = {
            val text = when (operationUIO) {
                is OperationArrayUIO -> R.string.array
                is OperationVariableUIO -> R.string.variable
                else -> 0
            }
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        text = {
            Column(

            ) {
                val text = when (operationUIO) {
                    is OperationArrayUIO -> R.string.edit_array
                    is OperationVariableUIO -> R.string.edit_variable
                    else -> 0
                }
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                        focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                        errorContainerColor = red,
                    )
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    when (operationUIO) {
                        is OperationVariableUIO -> viewModel.updateName(parent, operationUIO, input)
                        is OperationArrayUIO -> viewModel.updateName(parent, operationUIO, input)
                        else -> {}
                    }
                    onDismissRequest()
                }
            ) {
                Text(
                    stringResource(R.string.confirm),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    stringResource(R.string.dismiss),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.primary,
    )
}
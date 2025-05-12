package hitsedu.board.components

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
import hitsedu.board.BoardViewModel
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.operation.Operation
import hitsedu.ui_kit.models.operation.OperationArray
import hitsedu.ui_kit.models.operation.OperationConditionElse
import hitsedu.ui_kit.models.operation.OperationConditionIf
import hitsedu.ui_kit.models.operation.OperationValue
import hitsedu.ui_kit.models.operation.OperationVariable
import hitsedu.ui_kit.theme.red

@Composable
fun EditAlertDialog(
    operation: Operation,
    viewModel: BoardViewModel,
    onDismissRequest: () -> Unit,
) {
    var input by remember { mutableStateOf("") }

    LaunchedEffect(operation) {
        input = when (operation) {
            is OperationVariable -> operation.name
            is OperationValue -> operation.values
            else -> ""
        }
    }

    AlertDialog(
        title = {
            val text = when (operation) {
                is OperationArray -> R.string.array
                is OperationConditionElse -> R.string.conditions
                is OperationConditionIf -> R.string.conditions
                is OperationValue -> R.string.value
                is OperationVariable -> R.string.variable
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
                val text = when (operation) {
                    is OperationArray -> R.string.edit_array
                    is OperationConditionElse -> R.string.edit_condition
                    is OperationConditionIf -> R.string.edit_condition
                    is OperationValue -> R.string.edit_value
                    is OperationVariable -> R.string.edit_variable
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
                    when (operation) {
                        is OperationVariable -> {
                            viewModel.changeVariableName(operation, input)
                        }

                        is OperationValue -> {
                            viewModel.changeValue(operation, input)
                        }

                        else -> Unit
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
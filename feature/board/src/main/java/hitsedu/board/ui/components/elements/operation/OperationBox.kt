package hitsedu.board.ui.components.elements.operation


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.EditNameAlertDialog
import hitsedu.ui_kit.R
import hitsedu.ui_kit.components.ShowErrorAlertDialog
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.theme.red

@Composable
fun OperationBox(
    parent: ScopeUIO,
    operationUIO: OperationUIO,
    viewModel: BoardViewModel,
    backgroundColor: Color,
    onDeleteClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    val isValid = operationUIO is OperationVariableUIO ||
            operationUIO is OperationArrayUIO || operationUIO is OperationArrayIndexUIO
    val openAlertDialog = remember { mutableStateOf(false) }
    val isShowError = remember { mutableStateOf(false) }

    val borderColor = if (operationUIO.e == null) MaterialTheme.colorScheme.onPrimary else red

    when {
        openAlertDialog.value -> {
            EditNameAlertDialog(
                parent = parent,
                operationUIO = operationUIO,
                viewModel = viewModel,
                onDismissRequest = { openAlertDialog.value = false },
            )
        }

        isShowError.value && operationUIO.e != null -> {
            ShowErrorAlertDialog(
                message = operationUIO.e!!,
                onDismissRequest = { isShowError.value = false },
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp)
            .wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .border(
                    2.dp,
                    borderColor,
                    RoundedCornerShape(0.dp, 24.dp, 24.dp, 12.dp)
                )
                .background(
                    backgroundColor,
                    RoundedCornerShape(0.dp, 24.dp, 24.dp, 12.dp),
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp,
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    openAlertDialog.value = true && isValid
                },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (operationUIO.e != null) {
                IconButton(
                    onClick = {
                        isShowError.value = true
                    },
                    modifier = Modifier
                        .size(20.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_e),
                        contentDescription = "Show error",
                        tint = red,
                        modifier = Modifier,
                    )
                }
            }
            content()
        }
        Box(
            modifier = Modifier
                .size(20.dp)
                .offset((-8).dp, (-8).dp)
                .align(Alignment.TopStart)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .border(
                    2.dp,
                    backgroundColor,
                    CircleShape
                ),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_delete),
                    contentDescription = "Delete",
                    modifier = Modifier.size(12.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        // TODO("Волнистое подчеркивание")
    }
}
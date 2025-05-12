package hitsedu.board.components.elements


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
import hitsedu.board.BoardViewModel
import hitsedu.board.components.EditAlertDialog
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.operation.Operation

@Composable
fun OperationContainer(
    operation: Operation? = null,
    viewModel: BoardViewModel? = null,
    backgroundColor: Color,
    onDeleteClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    when {
        openAlertDialog.value -> {
            EditAlertDialog(
                operation = operation!!,
                viewModel = viewModel!!,
                onDismissRequest = { openAlertDialog.value = false },
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
                    openAlertDialog.value = true
                },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
    }
}
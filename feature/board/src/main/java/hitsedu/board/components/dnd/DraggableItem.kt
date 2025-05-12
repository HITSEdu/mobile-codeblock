package hitsedu.board.components.dnd

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import hitsedu.board.BoardViewModel

@Composable
fun <T> DraggableItem(
    dataToDrop: T,
    viewModel: BoardViewModel,
    onDragStart: () -> Unit = { },
    onDragStop: () -> Unit = { },
    content: @Composable (() -> Unit),
) {

    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = Modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(
                    Offset.Zero
                )
            }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = {
                        onDragStart()
                        viewModel.startDragging()
                        currentState.dataToDrop = dataToDrop
                        currentState.isDragging = true
                        currentState.dragPosition = currentPosition + it
                        currentState.draggableComposable = content
                    },
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                    },
                    onDragEnd = {
                        onDragStop()
                        viewModel.stopDragging()
                        currentState.isDragging = false
                        currentState.dragOffset = Offset.Zero
                    },
                    onDragCancel = {
                        onDragStop()
                        viewModel.stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                    })
            }) { content() }
}
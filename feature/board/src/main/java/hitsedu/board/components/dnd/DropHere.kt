package hitsedu.board.components.dnd

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import kotlin.reflect.KClass
import kotlin.reflect.cast

@Composable
fun <T : Any> DropHere(
    clazz: KClass<T>,
    content: @Composable BoxScope.(isInBound: Boolean, data: T?) -> Unit
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .onGloballyPositioned {
                it.boundsInWindow().let { rect ->
                    isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
                }
            }
    ) {
        val data: T? =
            if (isCurrentDropTarget && !dragInfo.isDragging) {
                dragInfo.dataToDrop
                    .takeIf { clazz.isInstance(it) }
                    ?.let { clazz.cast(it) }
            } else null
        content(isCurrentDropTarget, data)
    }
}
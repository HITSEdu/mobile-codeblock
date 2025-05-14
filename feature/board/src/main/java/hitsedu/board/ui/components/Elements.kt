package hitsedu.board.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.array.ArrayContent
import hitsedu.board.ui.components.elements.condition.ConditionContent
import hitsedu.board.ui.components.elements.function.FunctionContent
import hitsedu.board.ui.components.elements.loop.LoopContent
import hitsedu.board.ui.components.elements.output.OutputContent
import hitsedu.board.ui.components.elements.variable.VariableContent
import hitsedu.ui_kit.utils.ELEMENT_COLORS
import hitsedu.ui_kit.utils.Elements
import hitsedu.ui_kit.utils.TITLES

@Composable
fun Elements(
    viewModel: BoardViewModel,
    onDragStart: () -> Unit,
    onDragStop: () -> Unit,
) {
    var selected by remember { mutableStateOf(Elements.Variable) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary.copy(0.3f),
                shape = RoundedCornerShape(0.dp, 24.dp, 0.dp, 0.dp),
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(0.dp, 24.dp, 0.dp, 0.dp),
                )
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TITLES.forEach { (element, title) ->
                ElementItem(
                    title = title,
                    color = ELEMENT_COLORS[element]!!,
                    isSelected = selected == element,
                    onClick = { selected = element }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            when (selected) {
                Elements.Variable -> VariableContent(
                    viewModel,
                    onDragStart,
                    onDragStop,
                )

                Elements.Condition -> ConditionContent(
                    viewModel,
                    onDragStart,
                    onDragStop,
                )

                Elements.Loop -> LoopContent()
                Elements.Array -> ArrayContent()
                Elements.Function -> FunctionContent()
                Elements.Output -> OutputContent()
                Elements.Value -> {}
            }
        }
    }
}
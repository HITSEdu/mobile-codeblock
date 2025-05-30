package hitsedu.board.components

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
import hitsedu.board.BoardViewModel
import hitsedu.board.components.elements.operation.array.ArrayContent
import hitsedu.board.components.elements.operation.condition.ConditionContent
import hitsedu.board.components.elements.operation.function.FunctionContent
import hitsedu.board.components.elements.operation.loop.LoopContent
import hitsedu.board.components.elements.operation.output.OutputContent
import hitsedu.board.components.elements.operation.variable.VariableContent
import hitsedu.board.components.elements.value.ValueContent
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.paddingMedium
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.red
import hitsedu.ui_kit.theme.spaceMedium
import hitsedu.ui_kit.utils.ELEMENT_COLORS
import hitsedu.ui_kit.utils.Elements
import hitsedu.ui_kit.utils.TITLES

@Composable
fun Elements(
    viewModel: BoardViewModel,
    onDragStart: () -> Unit,
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
        verticalAlignment = Alignment.Bottom,
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(0.dp, 24.dp, 0.dp, 0.dp),
                )
                .padding(
                    horizontal = paddingLarge,
                    vertical = paddingMedium,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spaceMedium),
        ) {
            TITLES.forEach { (element, title) ->
                ElementItem(
                    title = title,
                    color = ELEMENT_COLORS[element] ?: red,
                    isSelected = selected == element,
                    onClick = { selected = element }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = paddingLarge,
                    vertical = paddingSmall,
                ),
            verticalArrangement = Arrangement.Bottom,
        ) {
            when (selected) {
                Elements.Variable -> VariableContent(
                    viewModel,
                    onDragStart,
                )

                Elements.Condition -> ConditionContent(
                    viewModel,
                    onDragStart,
                )

                Elements.Loop -> LoopContent(
                    viewModel,
                    onDragStart,
                )

                Elements.Array -> ArrayContent(
                    viewModel,
                    onDragStart,
                )

                Elements.Function -> FunctionContent()
                Elements.Output -> OutputContent(
                    viewModel,
                    onDragStart,
                )

                Elements.Value -> ValueContent(
                    viewModel,
                    onDragStart,
                )
            }
        }
    }
}
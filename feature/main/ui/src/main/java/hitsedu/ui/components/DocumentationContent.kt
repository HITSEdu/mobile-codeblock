package hitsedu.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.components.elements.value.ValueMock
import hitsedu.feature.main.ui.R
import hitsedu.ui.components.documentation.ExpandedItem
import hitsedu.ui.components.documentation.ValueDoc
import hitsedu.ui.components.documentation.VariableDoc

@Composable
fun DocumentationContent() {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .height(512.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = stringResource(hitsedu.ui_kit.R.string.documentation),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_basics
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Базовые операции разделяются на 2 группы:\n\t1. Операторы\n\t2. Значения\n" +
                                "Чтобы воспользоваться операцией, нужно с помощью долгого касания перетащить " +
                                "ее в любое допустимое место. Чтобы отредактировать имя массива/переменной" +
                                "достаточно нажать на нужную операцию и ввести новое значение.\n" +
                                "Codeblock имеет динамическую типизацию.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_value
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Внутри значения может располагаться все:\n" +
                                "Математическая операция, логическая операция, имя переменной/массива, " +
                                "если операция была объявлена ранее, строка " +
                                "(обязательно в кавычках!)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValueDoc("10 + 92")
                    ValueDoc("\"Привет!\"")
                    ValueDoc("array_name")
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_variable
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                        ),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Для объвления и использования переменной используется один оператор, " +
                                "соответсвенно вы не можете объявить две переменные с одинаковым названием.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    VariableDoc("a", "5")
                    VariableDoc("b", "a")
                    Text(
                        text = "В таком случае, значение переменной b будет равняться 5",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    VariableDoc("a", "5")
                    VariableDoc("b", "\"a\"")
                    Text(
                        text = "Но если дать значение b равное \"a\", то тип переменной b будет строкой",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}
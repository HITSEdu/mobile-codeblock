package hitsedu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hitsedu.ui.components.documentation.ArrayDoc
import hitsedu.ui.components.documentation.ArrayIndexDoc
import hitsedu.ui.components.documentation.ConditionElseDoc
import hitsedu.ui.components.documentation.ConditionIfDoc
import hitsedu.ui.components.documentation.ExpandedItem
import hitsedu.ui.components.documentation.LoopDoc
import hitsedu.ui.components.documentation.OutputDoc
import hitsedu.ui.components.documentation.ValueDoc
import hitsedu.ui.components.documentation.VariableDoc
import hitsedu.ui_kit.theme.red

@Composable
fun DocumentationContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                max = 512.dp,
            ),
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
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Базовые операции разделяются на 2 группы:\n\t1. Операторы\n\t2. Значения\n" +
                                "Чтобы воспользоваться операцией, нужно с помощью долгого касания перетащить " +
                                "ее в любое допустимое место. Чтобы отредактировать имя массива/переменной" +
                                "достаточно нажать на нужную операцию и ввести новое значение.\n\n" +
                                "Codeblock имеет динамическую типизацию.\n\n" +
                                "Каждая доска имеет автосохранение после каждого действия!",
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
                        .padding(8.dp),
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
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Для объвления и использования переменной используется один оператор, " +
                                "соответсвенно вы не можете объявить две переменные с одинаковым названием.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    VariableDoc("a", " 5 ")
                    VariableDoc("b", " a ")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "В таком случае, значение переменной b будет равняться 5",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    VariableDoc("a", " 5 ")
                    VariableDoc("b", " \"a\" ")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Но если дать значение b равное \"a\", то тип переменной b будет строкой",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_arrays
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Массивы представленные в языке являются статическими.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ArrayDoc("a", listOf("1", "2", "3"))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Чтобы изменить значение элемента массива по индексу, " +
                                "нужно воспользоваться соответсвующим элементом.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ArrayIndexDoc("a", "1", "47")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "После этой операции массив [1, 2, 3] преобразуется в [1, 47, 3]",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ArrayDoc("a", listOf("1", "47", "3"))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Чтобы взять значение массива по индексу используется следующая конструкция",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    VariableDoc("var", " a[0] ")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "В такой ситуации значениние переменной \"var\" будет равно 1",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    VariableDoc("var", " 1 ")
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_condition
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Условный оператор имеет две части: If и Else. " +
                                "Оператор Else всегда идет после оператора If в одном скопе, а не в дочернем.\n\n" +
                                "Поддерживаемые логические операции:\n" +
                                "\t1. Логическое ИЛИ - \"||\"\n" +
                                "\t2. Логическое И - \"&&\"\n" +
                                "\t3. Операторы сравнения: \"==\", \"!=\", \">\", \">=\", \"<\", \"<=\"\n",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    ConditionIfDoc("a > 3 && a != 7")
                    ConditionElseDoc()
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_loop
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Циклы представлены оператором For.\n\n" +
                                "Первое значение, которое получает оператор - это название переменной для итерации. " +
                                "Второе значение - условие выхода из цикла. " +
                                "Третье - действие, если условие выполняется.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LoopDoc("i = 0", "i < 5", "i = i + 1")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Если необходимо выполнить реверсивный цикл, то в поле значение следует прописать -1",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LoopDoc("i = 5", "i > 0", "i = i - 1")
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_output
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Блок вывода принимает на вход одно значение для вывода " +
                                "и печатает после него символ переноса строки",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutputDoc("\"Hello\"")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .size(156.dp, 36.dp)
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(0.3f))
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = ">",
                            style = MaterialTheme.typography.titleSmall,
                            color = red,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Hello",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Также можно совершать вывод переменной или массива",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    VariableDoc("var", " 5 ")
                    Spacer(modifier = Modifier.height(2.dp))
                    ArrayDoc("arr", listOf("1", "3", "5", "7", "9"))
                    Spacer(modifier = Modifier.height(2.dp))
                    OutputDoc("var")
                    Spacer(modifier = Modifier.height(2.dp))
                    OutputDoc("arr")
                    Spacer(modifier = Modifier.height(2.dp))
                    OutputDoc("arr[4]")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Вывод:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .width(156.dp)
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(0.3f))
                            .padding(
                                vertical = 4.dp,
                                horizontal = 12.dp,
                            ),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Text(
                                text = ">",
                                style = MaterialTheme.typography.titleSmall,
                                color = red,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "5",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Text(
                                text = ">",
                                style = MaterialTheme.typography.titleSmall,
                                color = red,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "[1, 3, 5, 7, 9]",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Text(
                                text = ">",
                                style = MaterialTheme.typography.titleSmall,
                                color = red,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "9",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                }
            }
        }
    }
}
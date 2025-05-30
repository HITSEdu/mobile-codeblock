package hitsedu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import hitsedu.ui_kit.theme.border
import hitsedu.ui_kit.theme.paddingLarge
import hitsedu.ui_kit.theme.paddingMedium
import hitsedu.ui_kit.theme.paddingSmall
import hitsedu.ui_kit.theme.red
import hitsedu.ui_kit.theme.shapeMedium
import hitsedu.ui_kit.theme.size156
import hitsedu.ui_kit.theme.size36
import hitsedu.ui_kit.theme.size512
import hitsedu.ui_kit.theme.size8
import hitsedu.ui_kit.theme.spaceLarge
import hitsedu.ui_kit.theme.spaceMedium

@Composable
fun DocumentationContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                max = size512,
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
            Spacer(modifier = Modifier.height(size8))
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_basics,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Базовые операции разделяются на 2 группы:\n\t1. Операторы\n\t2. Значения\n\n" +
                                "[!] Чтобы воспользоваться операцией, нужно с помощью долгого касания перетащить " +
                                "ее в любое допустимое место.\n\n* " +
                                "[!] Чтобы отредактировать имя массива/переменной " +
                                "достаточно нажать на нужную операцию и ввести новое значение.\n\n",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Text(
                        text = "Интерпретатор имеет динамическую типизацию и поддерживает 4 типа данных: " +
                                "String, Boolean, Integer, Double.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = red,
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_value,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopStart,
                    ) {
                        Text(
                            text = "Внутри значения может располагаться все:\n\n" +
                                    "\t1. Математическая/логическая операция\n\t2. Имя переменной/массива\n" +
                                    "\t3. Строка в кавычках\n",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Spacer(modifier = Modifier.height(size8))
                    ValueDoc("(10 + 92) * 4")
                    ValueDoc("\"Привет!\"")
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_variable,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Для объвления и использования переменной используется один оператор, " +
                                "соответсвенно вы не можете объявить две переменные с одинаковым названием.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    VariableDoc("a", " 5 ")
                    VariableDoc("b", " a ")
                    Text(
                        text = "В таком случае, значение переменной b будет равняться 5",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                    )
                    VariableDoc("a", " 5 ")
                    VariableDoc("b", " \"a\" ")
                    Text(
                        text = "Здесь b имеет строковый тип данных",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_arrays,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopStart,
                    ) {
                        Text(
                            text = "Массивы представленные в языке являются статическими.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    ArrayDoc("a", listOf("1", "2", "3"))
                    Text(
                        text = "Чтобы изменить значение элемента массива по индексу, " +
                                "нужно воспользоваться соответсвующим элементом.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    ArrayIndexDoc("a", "1", "47")
                    Text(
                        text = "После этой операции массив [1, 2, 3] преобразуется в [1, 47, 3]",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                    )
                    ArrayDoc("a", listOf("1", "47", "3"))
                    Text(
                        text = "Чтобы взять значение массива по индексу используется следующая конструкция",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    VariableDoc("var", " a[0] ")
                    Text(
                        text = "В такой ситуации значениние переменной \"var\" будет равно 1",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_condition,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopStart,
                    ) {
                        Text(
                            text = "Условный оператор имеет две части: If и Else.\n\n" +
                                    "Поддерживаемые логические операции:\n" +
                                    "\t1. Логические - \"||\", \"&&\"\n" +
                                    "\t2. Операторы сравнения: \"==\", \"!=\", \">\", \">=\", \"<\", \"<=\"\n",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    ConditionIfDoc("a > 3 && a != 7")
                    ConditionElseDoc()
                    Text(
                        text = "Оператор Else всегда идет после оператора If в одном скопе, а не в дочернем!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = red,
                    )
                }
            }
        }
        item {
            ExpandedItem(
                title = hitsedu.ui_kit.R.string.docs_loop,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceMedium),
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
                    LoopDoc("i = 0", "i < 5", "i + 1")
                    Text(
                        text = "Если необходимо выполнить реверсивный цикл, то в поле значение следует прописать -1",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    LoopDoc("i = 5", "i > 0", "i - 1")
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
                        .padding(paddingMedium),
                    verticalArrangement = Arrangement.spacedBy(spaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Блок вывода принимает на вход одно значение для вывода " +
                                "и печатает после него символ переноса строки",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    OutputDoc("\"Hello\"")
                    Row(
                        modifier = Modifier
                            .size(size156, size36)
                            .border(
                                width = border,
                                shape = RoundedCornerShape(shapeMedium),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(0.3f))
                            .padding(horizontal = paddingLarge),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spaceMedium),
                    ) {
                        Text(
                            text = ">",
                            style = MaterialTheme.typography.titleSmall,
                            color = red,
                        )
                        Spacer(modifier = Modifier.width(size8))
                        Text(
                            text = "Hello",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Text(
                        text = "Также можно совершать вывод переменной или массива",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    VariableDoc("var", " 5 ")
                    ArrayDoc("arr", listOf("1", "3", "5", "7", "9"))
                    OutputDoc("var")
                    OutputDoc("arr")
                    OutputDoc("arr[4]")
                    Text(
                        text = "Вывод:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Column(
                        modifier = Modifier
                            .width(size156)
                            .border(
                                width = border,
                                shape = RoundedCornerShape(shapeMedium),
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(0.3f))
                            .padding(
                                vertical = paddingSmall,
                                horizontal = paddingLarge,
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
                            Spacer(modifier = Modifier.width(size8))
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
                            Spacer(modifier = Modifier.width(size8))
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
                            Spacer(modifier = Modifier.width(size8))
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
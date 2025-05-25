package hitsedu.ui_kit.utils

import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

object TemplateBoards {

    // TODO("create templates")
    private val templateFibonacci = ProjectUIO(
        caption = "Fibonacci",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(

            ),
            id = 10L,
        ),
        id = 0L,
    )

    private val templateBubbleSort = ProjectUIO(
        caption = "Bubble Sort",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(
                OperationArrayUIO(
                    name = "a",
                    size = 0,
                    values = listOf(
                        ValueUIO(value = "500", id = 1506230890738339461),
                        ValueUIO(value = "300", id = 7151336549943451010),
                        ValueUIO(value = "100", id = 8724933754161821789),
                        ValueUIO(value = "200", id = 1366433372893072558)
                    ),
                    id = 8705357761640089447L
                ),
                OperationForUIO(
                    scope = ScopeUIO(
                        operationUIOS = listOf(
                            OperationForUIO(
                                scope = ScopeUIO(
                                    operationUIOS = listOf(
                                        OperationIfUIO(
                                            scope = ScopeUIO(
                                                operationUIOS = listOf(
                                                    OperationVariableUIO(
                                                        name = "tmp",
                                                        value = ValueUIO(
                                                            value = "a[j]",
                                                            id = 8146297731257992574
                                                        ),
                                                        id = 6206427293839493898
                                                    ),
                                                    OperationArrayIndexUIO(
                                                        name = "a",
                                                        index = ValueUIO(
                                                            value = "j",
                                                            id = 6886235740306817115
                                                        ),
                                                        value = ValueUIO(
                                                            value = "a[j + 1]",
                                                            id = 7513477898769685237
                                                        ),
                                                        id = 7075354982127326476
                                                    ),
                                                    OperationArrayIndexUIO(
                                                        name = "a",
                                                        index = ValueUIO(
                                                            value = "j + 1",
                                                            id = 289295900709260474
                                                        ),
                                                        value = ValueUIO(
                                                            value = "tmp",
                                                            id = 7367586577262566639
                                                        ),
                                                        id = 8124181319005871789
                                                    )
                                                )
                                            ),
                                            value = ValueUIO(
                                                value = "a[j] > a[j + 1]",
                                                id = 325751051776702730
                                            ),
                                            id = 3801094404637055687
                                        )
                                    )
                                ),
                                variable = ValueUIO(value = "j = 0", id = 7308805161538049441),
                                condition = ValueUIO(
                                    value = "< 3", id = 1940319611165523968
                                ),
                                value = ValueUIO(value = "1", id = 2422223287350580335),
                                id = 7823909612345678925,
                            ),
                        )
                    ),
                    variable = ValueUIO(value = "i = 0", id = 7308805166531049491),
                    condition = ValueUIO(
                        value = "< 3", id = 7940319611035523968
                    ),
                    value = ValueUIO(value = "1", id = 2414543947350580335),
                    id = 7823909680555324825
                ),
                OperationOutputUIO(
                    value = ValueUIO(value = "a", id = 4163984363544393938),
                    id = 1055932761711107759
                )
            ),
            id = 11L,
        ),
        id = 1L,
    )

    private val templateFactorial = ProjectUIO(
        caption = "Factorial",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(

            ),
            id = 12L,
        ),
        id = 2L,
    )

    val templates = listOf(
        templateFibonacci,
        templateBubbleSort,
        templateFactorial,
    )
}
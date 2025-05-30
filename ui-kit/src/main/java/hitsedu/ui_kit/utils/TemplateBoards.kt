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
    private val templateFibonacci = ProjectUIO(
        caption = "Fibonacci",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(
                OperationVariableUIO(
                    name = "fib1",
                    value = ValueUIO(value = "1", id = 5444079422085973806),
                    id = 5474252861578428078
                ),
                OperationVariableUIO(
                    name = "fib2",
                    value = ValueUIO(value = "1", id = 4969679268660568987),
                    id = 1641607409238507608
                ),
                OperationVariableUIO(
                    name = "n",
                    value = ValueUIO(value = "8", id = 8046254054816703905),
                    id = 5833065406319606471
                ),
                OperationForUIO(
                    variable = ValueUIO(value = "i = 0", id = 1401927823237040063),  // Исправлено на "i = 0"
                    condition = ValueUIO(value = "i < n", id = 959695727859821036),
                    value = ValueUIO(value = "i + 1", id = 7608058419191111832),
                    scope = ScopeUIO(
                        operationUIOS = listOf(
                            OperationVariableUIO(
                                name = "tmp",
                                value = ValueUIO(value = "fib1", id = 6205523753785772260),
                                id = 8693895756309229491
                            ),
                            OperationVariableUIO(
                                name = "fib1",
                                value = ValueUIO(value = "fib2", id = 4721560872672198346),
                                id = 3542015424851665495
                            ),
                            OperationVariableUIO(
                                name = "fib2",
                                value = ValueUIO(value = "tmp + fib2", id = 7224779749948345158),
                                id = 7467029361727140353
                            ),
                            OperationOutputUIO(
                                value = ValueUIO(value = "fib2", id = 8619160022746302408),
                                id = 8226619961881919031
                            )
                        ),
                        id = 1488148814,
                    ),
                    id = 4403605073771007443
                ),

                OperationOutputUIO(
                    value = ValueUIO(value = "fib2", id = 8619160022746302408),
                    id = 8226619961881919021
                )
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
                                    value = "j < (3 - i)", id = 1940319611165523968
                                ),
                                value = ValueUIO(value = "j + 1", id = 2422223287350580335),
                                id = 7823909612345678925,
                            ),
                        )
                    ),
                    variable = ValueUIO(value = "i = 0", id = 7308805166531049491),
                    condition = ValueUIO(
                        value = "i < 3", id = 7940319611035523968
                    ),
                    value = ValueUIO(value = "i + 1", id = 2414543947350580335),
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
                OperationVariableUIO(
                    name = "n",
                    value = ValueUIO(value = "6", id = 6135235190976617690),
                    id = 6718362626412260894
                ),
                OperationVariableUIO(
                    name = "factorial",
                    value = ValueUIO(value = "1", id = 2315848672946864609),
                    id = 8015185842331283356
                ),
                OperationForUIO(
                    scope = ScopeUIO(
                        operationUIOS = listOf(
                            OperationVariableUIO(
                                name = "factorial",
                                value = ValueUIO(value = "factorial * i", id = 4642992067738745861),
                                id = 6673364128309762137,
                            )
                        ),
                        id = 99999653
                    ),
                    variable = ValueUIO(value = "i = 2", id = 5139477128428804218),
                    condition = ValueUIO(value = "i < n + 1", id = 5595413765052933271),
                    value = ValueUIO(value = "i + 1", id = 8380124149128170437),
                    id = 6906137529296532862
                ),
                OperationOutputUIO(
                    value = ValueUIO(value = "factorial", id = 8801044252029357540),
                    id = 2089160020644639638
                )
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
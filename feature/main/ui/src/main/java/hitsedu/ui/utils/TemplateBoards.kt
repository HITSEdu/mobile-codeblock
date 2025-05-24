package hitsedu.ui.utils

import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO

object TemplateBoards {

    // TODO("create templates")
    private val templateBubbleSort = ProjectUIO(
        caption = "Bubble Sort",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(

            ),
            id = 10L,
        ),
        id = 0L,
    )

    private val templateFibonacci = ProjectUIO(
        caption = "Fibonacci",
        scale = 1f,
        scopeUIOS = emptyList(),
        globalScope = ScopeUIO(
            operationUIOS = listOf(

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
        templateBubbleSort,
        templateFibonacci,
        templateFactorial,
    )
}
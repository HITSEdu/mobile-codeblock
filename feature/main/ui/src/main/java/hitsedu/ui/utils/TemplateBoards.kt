package hitsedu.ui.utils

import hitsedu.data.models.ProjectDBO
import hitsedu.data.models.ScopeDBO

object TemplateBoards {

    // TODO("create templates")
    private val templateBubbleSort = ProjectDBO(
        caption = "Bubble Sort",
        scale = 1f,
        scopeDBOS = emptyList(),
        globalScope = ScopeDBO(
            operationDBOS = emptyList()
        ),
        id = 0L,
    )

    private val templateFibonacci = ProjectDBO(
        caption = "Fibonacci",
        scale = 1f,
        scopeDBOS = emptyList(),
        globalScope = ScopeDBO(
            operationDBOS = emptyList()
        ),
        id = 1L,
    )

    private val templateFactorial = ProjectDBO(
        caption = "Factorial",
        scale = 1f,
        scopeDBOS = emptyList(),
        globalScope = ScopeDBO(
            operationDBOS = emptyList()
        ),
        id = 2L,
    )

    val templates = listOf(
        templateBubbleSort,
        templateFibonacci,
        templateFactorial,
    )
}
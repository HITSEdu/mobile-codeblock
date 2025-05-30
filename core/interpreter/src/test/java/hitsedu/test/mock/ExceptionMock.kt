package hitsedu.test.mock

import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationOutput

object ExceptionMock {
    val EXPECTED_CANNOT_RESOLVE_VALUE = E(
        message = "Error processing expression: Cannot resolve value: a",
        blockId = 12,
    )

    val EXPECTED_INDEX_OUT_OF_BOUNDS = E(
        message = "",
        blockId = 12,
    )

    val cannotResolveValue = Scope(
        operations = listOf(
            OperationOutput(
                value = Value("a"),
                id = 12,
            ),
        ),
    )

    val indexOutOfBounds = Scope(
        operations = listOf(
            OperationOutput(
                value = Value("a"),
                id = 12,
            ),
        ),
    )
}
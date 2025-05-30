package hitsedu.test.mock

import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable

object OutputMock {
    const val EXPECTED_STRING_OUT = "\"Output string\""
    const val EXPECTED_MATH_OUT = "14"
    const val EXPECTED_LOGIC_OUT = "true"
    const val EXPECTED_VARIABLE_OUT = "94"

    val outputTestString = Scope(
        operations = listOf(
            OperationOutput(
                value = Value(EXPECTED_STRING_OUT),
                id = 0,
            ),
        ),
    )

    val outputTestMath = Scope(
        operations = listOf(
            OperationOutput(
                value = Value("(6 + (13 + 5)*2)/3"),
                id = 0,
            ),
        ),
    )

    val outputTestLogic = Scope(
        operations = listOf(
            OperationOutput(
                value = Value("5 < 13"),
                id = 0,
            ),
        ),
    )

    val outputTestVariable = Scope(
        operations = listOf(
            OperationVariable(
                name = "a",
                value = Value("13 + 17"),
                id = 0,
            ),
            OperationVariable(
                name = "b",
                value = Value("68 - 4"),
                id = 0,
            ),
            OperationOutput(
                value = Value("a + b"),
                id = 0,
            ),
        ),
    )
}
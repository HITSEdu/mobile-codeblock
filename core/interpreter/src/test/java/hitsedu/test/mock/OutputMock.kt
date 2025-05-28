package hitsedu.test.mock

import hitsedu.interpreter.models.Project
import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable

object OutputMock {
    val outputTest = Project(
        caption = "Test",
        scale = 1f,
        scopes = emptyList(),
        globalScope = Scope(
            operations = listOf(
                OperationVariable(
                    name = "a",
                    value = Value("140"),
                    id = 32,
                ),
                OperationVariable(
                    name = "b",
                    value = Value("6"),
                    id = 320,
                ),
                OperationArray(
                    name = "arr",
                    values = listOf(
                        Value("1"),
                        Value("2"),
                        Value("3"),
                        Value("4"),
                    ),
                    id = 3200,
                ),
                OperationOutput(
                    value = Value(
                        value = "\"Test\"",
                        id = 3,
                    ),
                    id = 2
                ),
                OperationOutput(
                    value = Value(
                        value = "4",
                        id = 30,
                    ),
                    id = 20
                ),
                OperationOutput(
                    value = Value(
                        value = "arr",
                        id = 3000,
                    ),
                    id = 2000
                ),
                OperationOutput(
                    value = Value(
                        value = "a + 1",
                        id = 30000,
                    ),
                    id = 20000
                ),
                OperationOutput(
                    value = Value(
                        value = "a + b",
                        id = 300000,
                    ),
                    id = 200000
                ),
                OperationOutput(
                    value = Value(
                        value = "arr[2]",
                        id = 3000000,
                    ),
                    id = 2000000
                ),
            ),
            id = 1,
        ),
        id = 0,
    )
}
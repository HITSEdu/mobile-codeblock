package hitsedu.test

import hitsedu.interpreter.InterpreterImpl
import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationArrayIndex
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import org.junit.Test
import kotlin.test.assertEquals

class TestArray {
    @Test
    fun testArray() {
        val interpreter = InterpreterImpl()
        val scope = Scope(
            id = 0,
            operations = listOf(
                OperationArray(
                    id = 8,
                    name = "arr",
                    values = listOf(
                        Value("1"),
                        Value("2"),
                        Value("3"),
                        Value("4"),
                        Value("5"),
                    ),
                ),
                OperationOutput(
                    id = 1,
                    value = Value("arr"),
                ),
                OperationArrayIndex(
                    id = 3,
                    name = "arr",
                    index = Value("0"),
                    value = Value("12"),
                ),
                OperationVariable(
                    id = 16,
                    name = "a",
                    value = Value("5 > 3 && (4 >= 1 || 3 == 7) || 5 <= 1")
                ),
                OperationOutput(
                    id = 2,
                    value = Value("arr[0] < arr[4] && a"),
                ),
            )
        )
        interpreter.process(scope)
        val output = interpreter.getConsole()
        assertEquals(
            expected = "[1, 2, 3, 4, 5]",
            actual = output[0].output,
            message = "[OUT]: Print array",
        )
        assertEquals(
            expected = "false",
            actual = output[1].output,
            message = "[OUT]: Logic with array",
        )
    }
}
package hitsedu.test

import hitsedu.interpreter.InterpreterImpl
import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.processor.process
import hitsedu.test.mock.OutputMock
import org.junit.Test
import kotlin.test.assertEquals

class TestInterpreter {
    @Test
    fun `ConsoleOutput return String value`() {
        val out = OutputMock.outputTest
            .globalScope
            .operations
            .filterIsInstance<OperationOutput>()
            .first()

        val variables = mutableListOf<OperationVariable>()
        val arrays = mutableListOf<OperationArray>()

        val result: ConsoleOutput = out.process(variables, arrays)
        assertEquals(
            ConsoleOutput("\"Test\"", null),
            result,
        )
    }

    @Test
    fun `ConsoleOutput return Int value`() {
        val out = OutputMock.outputTest
            .globalScope
            .operations
            .filterIsInstance<OperationOutput>()[1]

        val variables = mutableListOf<OperationVariable>()
        val arrays = mutableListOf<OperationArray>()

        val result: ConsoleOutput = out.process(variables, arrays)
        assertEquals(
            ConsoleOutput("4", null),
            result,
        )
    }

    @Test
    fun `ConsoleOutput return Array`() {
        val out = OutputMock.outputTest
            .globalScope
            .operations
            .filterIsInstance<OperationOutput>()[2]

        val variables = mutableListOf<OperationVariable>()
        val arrays = mutableListOf<OperationArray>()
        arrays.add(
            OperationArray(
                name = "arr",
                values = listOf(
                    Value("1"),
                    Value("2"),
                    Value("3"),
                    Value("4"),
                ),
                id = 3200,
            )
        )

        val result: ConsoleOutput = out.process(variables, arrays)
        assertEquals(
            ConsoleOutput("[1, 2, 3, 4]", null),
            result,
        )
    }

    @Test
    fun `Return list of ConsoleOutput`() {
        val interpreter = InterpreterImpl()
        interpreter.process(OutputMock.outputTest.globalScope)

        val outputs: List<ConsoleOutput> = interpreter.getConsole()

        assertEquals(
            expected = listOf(
                ConsoleOutput("\"Test\"", null),
                ConsoleOutput("4", null),
                ConsoleOutput("[1, 2, 3, 4]", null),
                ConsoleOutput("141", null),
                ConsoleOutput("146", null),
                ConsoleOutput("141", null),
                ConsoleOutput("3", null),
            ),
            actual = outputs
        )
    }
}
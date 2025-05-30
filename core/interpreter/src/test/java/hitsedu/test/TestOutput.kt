package hitsedu.test

import hitsedu.interpreter.InterpreterImpl
import hitsedu.test.mock.OutputMock
import kotlin.test.Test
import kotlin.test.assertEquals

class TestOutput {
    @Test
    fun `test string output`() {
        val interpreter = InterpreterImpl()

        interpreter.process(OutputMock.outputTestString)
        val output = interpreter.getConsole()[0]
        assertEquals(
            expected = "Output string",
            actual = output.output,
            message = "[Console output]: simple string",
        )
    }

    @Test
    fun `test math output`() {
        val interpreter = InterpreterImpl()

        interpreter.process(OutputMock.outputTestMath)
        val console = interpreter.getConsole()[0]

        assertEquals(
            expected = OutputMock.EXPECTED_MATH_OUT,
            actual = console.output,
            message = "[Console output]: math expression",
        )
    }

    @Test
    fun `test logic output`() {
        val interpreter = InterpreterImpl()

        interpreter.process(OutputMock.outputTestLogic)
        val console = interpreter.getConsole()[0]

        assertEquals(
            expected = OutputMock.EXPECTED_LOGIC_OUT,
            actual = console.output,
            message = "[Console output]: logic expression",
        )
    }

    @Test
    fun `test variable output`() {
        val interpreter = InterpreterImpl()

        interpreter.process(OutputMock.outputTestVariable)
        val console = interpreter.getConsole()[0]

        assertEquals(
            expected = OutputMock.EXPECTED_VARIABLE_OUT,
            actual = console.output,
            message = "[Console output]: variable",
        )
    }
}
package hitsedu.test

import hitsedu.interpreter.InterpreterImpl
import hitsedu.test.mock.ExceptionMock
import org.junit.Test
import kotlin.test.assertEquals

class TestException {

    @Test
    fun `test cannot resolve value`() {
        val interpreter = InterpreterImpl()

        interpreter.process(ExceptionMock.cannotResolveValue)
        val output = interpreter.getConsole()[0]
        assertEquals(
            expected = ExceptionMock.EXPECTED_CANNOT_RESOLVE_VALUE,
            actual = output.exception,
            message = "[E]: cannot resolve value",
        )
    }
}
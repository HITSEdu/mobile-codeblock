package hitsedu.interpreter

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.Scope

interface Interpreter {
    suspend fun process(scope: Scope)
    fun getConsole(): List<ConsoleOutput>
}
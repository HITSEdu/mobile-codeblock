package hitsedu.interpreter

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.Scope

interface Interpreter {
    fun process(scope: Scope)
    fun getConsole(): List<ConsoleOutput>
}
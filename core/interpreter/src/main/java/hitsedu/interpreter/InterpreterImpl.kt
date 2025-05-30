package hitsedu.interpreter

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.operation.Operation
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationArrayIndex
import hitsedu.interpreter.models.operation.OperationElse
import hitsedu.interpreter.models.operation.OperationFor
import hitsedu.interpreter.models.operation.OperationIf
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.interpreter.processor.process

class InterpreterImpl : Interpreter {
    private val variables = mutableListOf<OperationVariable>()
    private val arrays = mutableListOf<OperationArray>()
    private val console = mutableListOf<ConsoleOutput>()

    private val visited = mutableSetOf<Long>()
    private var prevOperation: Pair<Operation, Boolean>? = null

    override fun process(scope: Scope) {
        if (!visited.add(scope.id)) return
        for (operation in scope.operations) {
            when (operation) {
                is OperationVariable -> {
                    val e = operation.process(variables, arrays)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = Pair(operation, false)
                }

                is OperationArray -> {
                    val e = operation.process(variables, arrays)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = Pair(operation, false)
                }

                is OperationArrayIndex -> {
                    val e = operation.process(variables, arrays)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = Pair(operation, false)
                }

                is OperationIf -> {
                    val condition = operation.process(variables, arrays)
                    if (condition == null) {
                        console.add(ConsoleOutput("", E("Error in logic expression", operation.id)))
                        return
                    }
                    if (condition) {
                        process(operation.scope)
                    }
                    prevOperation = Pair(operation, condition)
                }

                is OperationElse -> {
                    val (prev, condition) = prevOperation ?: run {
                        console.add(ConsoleOutput("", E("If must be before else", operation.id)))
                        return
                    }
                    if (prev is OperationIf) {
                        if (!condition) {
                            process(operation.scope)
                        }
                    } else {
                        console.add(ConsoleOutput("", E("If must be before else", operation.id)))
                        return
                    }
                    prevOperation = Pair(operation, false)
                }

                is OperationFor -> {
                    val e = operation.process(variables, arrays, console)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = Pair(operation, false)
                }

                is OperationOutput -> {
                    console.add(operation.process(variables, arrays))
                    prevOperation = Pair(operation, false)
                }
            }
        }
    }

    override fun getConsole() = console.toList()
}
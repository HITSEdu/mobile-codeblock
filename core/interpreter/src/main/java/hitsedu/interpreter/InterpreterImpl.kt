package hitsedu.interpreter

import hitsedu.interpreter.models.ConsoleOutput
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
    private var prevOperation: Operation? = null

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
                    prevOperation = operation
                }

                is OperationArray -> {
                    val e = operation.process(variables, arrays)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = operation
                }

                is OperationArrayIndex -> {
                    val e = operation.process(variables, arrays)
                    if (e != null) {
                        console.add(ConsoleOutput("", e))
                        return
                    }
                    prevOperation = operation
                }

                is OperationIf -> {
                    if (operation.process(variables, arrays)) {
                        process(operation.scope)
                    }
                    prevOperation = operation
                }

                is OperationElse -> {
                    operation.process(prevOperation)
                    process(operation.scope)
                    prevOperation = operation
                }

                is OperationFor -> {
                    operation.process()
                    prevOperation = operation
                }

                is OperationOutput -> {
                    console.add(operation.process(variables, arrays))
                    prevOperation = operation
                }
            }
        }
    }

    override fun getConsole() = console.toList()
    fun getArrays() = arrays.toList()
    fun getVariables() = variables.toList()
}
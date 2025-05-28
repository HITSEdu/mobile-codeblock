package hitsedu.interpreter

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.Scope
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
//    private val prevOperation: Operation = TODO()

    override fun process(scope: Scope) {
        if (!visited.add(scope.id)) return
        for (operation in scope.operations) {
            when (operation) {
                is OperationVariable -> variables.add(operation.process(variables, arrays))
                is OperationArray -> arrays.add(operation.process(variables))
                is OperationArrayIndex -> {
                    val exec = operation.process(variables, arrays)
                    if (exec != null)
                        console.add(exec)
                }

                is OperationOutput -> console.add(operation.process(variables, arrays))
                is OperationIf -> {
                    if (operation.process(variables, arrays)) {
                        process(operation.scope)
                    }
                }

                is OperationElse -> {
                    process(operation.scope) // TODO("pass prev operation")
                }

                is OperationFor -> {
                    operation.process()
                }
            }
        }
    }

    override fun getConsole() = console
    fun getArrays() = arrays
    fun getVariables() = variables
}
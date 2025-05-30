package hitsedu.interpreter.processor

import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.operation.Operation
import hitsedu.interpreter.models.operation.OperationElse
import hitsedu.interpreter.models.operation.OperationIf

fun OperationElse.process(
    prevOperation: Operation?
): E? {
    if (prevOperation is OperationIf && prevOperation != null) {
        // TODO("process")
    }
    return E(
        message = "If must be before else",
        blockId = id,
    )
}
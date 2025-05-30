package hitsedu.board.viewModel

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.exception.ConsoleOutputUIO
import hitsedu.ui_kit.models.exception.EUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.copyScope

fun updateErrorsInScope(scope: ScopeUIO, consoleOutput: List<ConsoleOutputUIO>): ScopeUIO =
    copyScope(scope, scope.operationUIOS.map { op ->
        mapOperationWithError(op, consoleOutput)
    })

fun clearErrorsInScope(scope: ScopeUIO): ScopeUIO =
    copyScope(scope, scope.operationUIOS.map { op ->
        clearOperationErrors(op)
    })

private fun mapOperationWithError(op: OperationUIO, consoleOutput: List<ConsoleOutputUIO>): OperationUIO {
    val matched = consoleOutput.firstOrNull { it.e?.blockId == op.id }
    val newE: EUIO? = matched?.e

    return when (op) {
        is OperationVariableUIO ->
            OperationVariableUIO(
                id = op.id,
                name = op.name,
                value = op.value,
                e = newE?.message,
            )

        is OperationArrayUIO ->
            OperationArrayUIO(
                id = op.id,
                name = op.name,
                values = op.values,
                e = newE?.message,
            )

        is OperationArrayIndexUIO ->
            OperationArrayIndexUIO(
                id = op.id,
                name = op.name,
                index = op.index,
                value = op.value,
                e = newE?.message,
            )

        is OperationIfUIO ->
            OperationIfUIO(
                id = op.id,
                value = op.value,
                scope = updateErrorsInScope(op.scope, consoleOutput),
                e = newE?.message,
            )

        is OperationForUIO ->
            OperationForUIO(
                id = op.id,
                variable = op.variable,
                condition = op.condition,
                value = op.value,
                scope = updateErrorsInScope(op.scope, consoleOutput),
                e = newE?.message,
            )

        is OperationElseUIO ->
            OperationElseUIO(
                id = op.id,
                scope = updateErrorsInScope(op.scope, consoleOutput),
                e = newE?.message,
            )

        is OperationOutputUIO ->
            OperationOutputUIO(
                id = op.id,
                value = op.value,
                e = newE?.message,
            )

        else -> op
    }
}

private fun clearOperationErrors(op: OperationUIO): OperationUIO {
    return when (op) {
        is OperationVariableUIO ->
            op.copy(e = null)

        is OperationArrayUIO ->
            op.copy(e = null)

        is OperationArrayIndexUIO ->
            op.copy(e = null)

        is OperationIfUIO ->
            op.copy(
                e = null,
                scope = clearErrorsInScope(op.scope)
            )

        is OperationForUIO ->
            op.copy(
                e = null,
                scope = clearErrorsInScope(op.scope)
            )

        is OperationElseUIO ->
            op.copy(
                e = null,
                scope = clearErrorsInScope(op.scope)
            )

        is OperationOutputUIO ->
            op.copy(e = null)

        else -> op
    }
}
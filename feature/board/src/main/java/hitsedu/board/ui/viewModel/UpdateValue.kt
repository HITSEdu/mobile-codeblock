package hitsedu.board.ui.viewModel

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO
import hitsedu.ui_kit.utils.copyScope
import kotlin.random.Random

fun updateScopeValues(
    scope: ScopeUIO,
    parent: OperationUIO,
    oldValue: ValueUIO,
    newValue: ValueUIO,
): ScopeUIO = copyScope(scope, scope.operationUIOS.map { op ->
    if (op.id == parent.id) replaceValueInOperation(op, oldValue, newValue)
    else recurseNested(op, parent, oldValue, newValue)
})

private fun replaceValueInOperation(
    op: OperationUIO,
    oldValue: ValueUIO,
    newValue: ValueUIO,
): OperationUIO = when (op) {
    is OperationVariableUIO -> op.copy(value = newValue.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
    is OperationArrayUIO ->
        op.copy(
            values = op.values.map { v ->
                if (v.id == oldValue.id) newValue.copy(id = Random.nextLong(1, Long.MAX_VALUE)) else v
            }
        )

    is OperationIfUIO -> op.copy(value = newValue.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
    is OperationForUIO ->
        op.copy(
            variable = if (op.variable.id == oldValue.id) newValue.copy(
                id = Random.nextLong(
                    1,
                    Long.MAX_VALUE
                )
            ) else op.variable,
            condition = if (op.condition.id == oldValue.id) newValue.copy(
                id = Random.nextLong(
                    1,
                    Long.MAX_VALUE
                )
            ) else op.condition,
            value = if (op.value.id == oldValue.id) newValue.copy(
                id = Random.nextLong(
                    1,
                    Long.MAX_VALUE
                )
            ) else op.value,
        )

    is OperationOutputUIO -> op.copy(value = newValue.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
    is OperationArrayIndexUIO -> op.copy(
        index = if (op.index.id == oldValue.id) newValue.copy(
            id = Random.nextLong(
                1,
                Long.MAX_VALUE
            )
        ) else op.index,
        value = if (op.value.id == oldValue.id) newValue else op.value,
    )

    else -> op
}


private fun recurseNested(
    op: OperationUIO,
    parent: OperationUIO,
    oldValue: ValueUIO,
    newValue: ValueUIO,
): OperationUIO = when (op) {
    is OperationIfUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
    is OperationForUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
    is OperationElseUIO -> op.copy(scope = updateScopeValues(op.scope, parent, oldValue, newValue))
    else -> op
}
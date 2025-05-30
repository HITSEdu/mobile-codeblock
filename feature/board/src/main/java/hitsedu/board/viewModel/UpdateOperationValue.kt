package hitsedu.board.viewModel

import hitsedu.board.utils.UpdateType
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

fun updateScopeWithOperation(
    scope: ScopeUIO,
    parent: OperationUIO,
    newValue: ValueUIO,
    type: UpdateType,
): ScopeUIO = copyScope(scope, scope.operationUIOS.map { op ->
    if (op.id == parent.id) updateOperationWithValue(op, newValue, type)
    else updateNestedOperation(op, parent, newValue, type)
})

private fun updateOperationWithValue(
    op: OperationUIO,
    value: ValueUIO,
    type: UpdateType,
): OperationUIO = when (op) {
    is OperationVariableUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(value = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
            UpdateType.DELETE -> op.copy(value = ValueUIO())
            UpdateType.REPLACE -> op
        }

    is OperationArrayUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(
                values = op.values + value.copy(
                    id = Random.nextLong(1, Long.MAX_VALUE)
                )
            )

            UpdateType.DELETE -> op.copy(values = op.values.filterNot { it == value })
            UpdateType.REPLACE -> op
        }

    is OperationIfUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(value = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
            UpdateType.DELETE -> op.copy(value = ValueUIO())
            UpdateType.REPLACE -> op
        }

    is OperationForUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(
                variable = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)),
                condition = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)),
                value = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)),
            )

            UpdateType.DELETE -> op.copy(
                variable = ValueUIO(),
                condition = ValueUIO(),
                value = ValueUIO(),
            )

            UpdateType.REPLACE -> op
        }

    is OperationOutputUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(value = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)))
            UpdateType.DELETE -> op.copy(value = ValueUIO())
            UpdateType.REPLACE -> op
        }

    is OperationArrayIndexUIO ->
        when (type) {
            UpdateType.ADD -> op.copy(
                index = value.copy(id = Random.nextLong(1, Long.MAX_VALUE)),
                value = value.copy(id = Random.nextLong(1, Long.MAX_VALUE))
            )

            UpdateType.DELETE -> op.copy(
                index = ValueUIO(),
                value = ValueUIO(),
            )

            UpdateType.REPLACE -> op
        }

    else -> op
}

private fun updateNestedOperation(
    op: OperationUIO,
    parent: OperationUIO,
    newValue: ValueUIO,
    type: UpdateType,
): OperationUIO = when (op) {
    is OperationIfUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
    is OperationForUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
    is OperationElseUIO -> op.copy(scope = updateScopeWithOperation(op.scope, parent, newValue, type))
    else -> op
}
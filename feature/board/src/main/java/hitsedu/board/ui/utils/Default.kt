package hitsedu.board.ui.utils

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

object Default {
    val value = ValueUIO("10 + 3 - 8")

    val variable = OperationVariableUIO(
        name = "var",
        value = ValueUIO(),
    )

    val conditionIf = OperationIfUIO(
        scope = ScopeUIO(operationUIOS = emptyList()),
        value = ValueUIO(),
    )

    val conditionElse = OperationElseUIO(
        scope = ScopeUIO(operationUIOS = emptyList())
    )

    val loop = OperationForUIO(
        scope = ScopeUIO(operationUIOS = emptyList()),
        variable = variable,
        condition = ValueUIO(),
        value = ValueUIO(),
    )

    val array = OperationArrayUIO(
        name = "arr",
        size = 0,
        values = emptyList(),
    )

    val output = OperationOutputUIO(
        value = ValueUIO(),
    )
}
package hitsedu.board.ui.utils

import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

object Default {
    val value = ValueUIO("0")

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
        variable = OperationVariableUIO(
            name = "i",
            value = ValueUIO(),
        ),
        condition = ValueUIO(),
        value = ValueUIO(),
    )

    val array = OperationArrayUIO(
        name = "arr",
        values = emptyList(),
    )

    val arrayIndex = OperationArrayIndexUIO(
        name = "arr",
        index = ValueUIO(),
        value = ValueUIO(),
    )

    val output = OperationOutputUIO(
        value = ValueUIO(),
    )
}
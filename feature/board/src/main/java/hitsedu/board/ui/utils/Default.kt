package hitsedu.board.ui.utils

import hitsedu.ui_kit.models.data.DataType
import hitsedu.ui_kit.models.operation.OperationUIOConditionIf
import hitsedu.ui_kit.models.operation.OperationUIOValue
import hitsedu.ui_kit.models.operation.OperationUIOVariable
import hitsedu.ui_kit.models.scope.ScopeConditionUIO

object Default {
    val value = OperationUIOValue("0")

    val variable = OperationUIOVariable(
        name = "new_variable",
        type = DataType.Integer,
        value = OperationUIOValue(""),
    )

    val condition = OperationUIOConditionIf(
        scope = ScopeConditionUIO(
            operationUIOS = emptyList(),
            childScopeUIOS = emptyList(),
        ),
        value = OperationUIOValue(""),
    )
}
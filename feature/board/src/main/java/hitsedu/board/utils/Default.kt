package hitsedu.board.utils

import hitsedu.ui_kit.models.data.DataType
import hitsedu.ui_kit.models.operation.OperationConditionIf
import hitsedu.ui_kit.models.operation.OperationValue
import hitsedu.ui_kit.models.operation.OperationVariable
import hitsedu.ui_kit.models.scope.ConditionScope

object Default {
    val value = OperationValue("0")

    val variable = OperationVariable(
        name = "new_variable",
        type = DataType.Integer,
        value = OperationValue(""),
    )

    val condition = OperationConditionIf(
        scope = ConditionScope(
            operations = emptyList(),
            childScopes = emptyList(),
        ),
        value = OperationValue(""),
    )
}
package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ConditionScope

data class OperationConditionIf(
    override val scope: ConditionScope,
    val value: OperationValue,
) : OperationCondition()
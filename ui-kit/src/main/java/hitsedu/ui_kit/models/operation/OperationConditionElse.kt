package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ConditionScope

data class OperationConditionElse(
    override val scope: ConditionScope,
    val parentIf: OperationConditionIf,
) : OperationCondition()
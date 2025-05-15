package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ScopeConditionUIO

data class OperationUIOConditionIf(
    override val scope: ScopeConditionUIO,
    val value: OperationUIOValue,
) : OperationUIOCondition()
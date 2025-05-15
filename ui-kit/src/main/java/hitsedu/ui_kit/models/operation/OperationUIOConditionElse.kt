package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ScopeConditionUIO

data class OperationUIOConditionElse(
    override val scope: ScopeConditionUIO,
    val parentIf: OperationUIOConditionIf,
) : OperationUIOCondition()
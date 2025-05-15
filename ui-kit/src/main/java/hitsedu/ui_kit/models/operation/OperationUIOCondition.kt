package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ScopeConditionUIO

sealed class OperationUIOCondition : OperationUIO() {
    abstract val scope: ScopeConditionUIO
}
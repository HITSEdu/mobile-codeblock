package hitsedu.ui_kit.models.operation

import hitsedu.ui_kit.models.scope.ConditionScope

sealed class OperationCondition: Operation() {
    abstract val scope: ConditionScope
}
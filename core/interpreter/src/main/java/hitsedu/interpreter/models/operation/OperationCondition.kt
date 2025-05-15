package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.scope.ScopeLocalCondition

sealed class OperationCondition : Operation() {
    abstract val scope: ScopeLocalCondition
}
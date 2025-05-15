package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.scope.ScopeLocalCondition

data class OperationConditionElse(
    override val scope: ScopeLocalCondition,
    val parentIf: OperationConditionIf,
) : OperationCondition()
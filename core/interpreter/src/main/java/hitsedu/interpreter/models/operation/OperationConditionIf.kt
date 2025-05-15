package hitsedu.interpreter.models.operation

import hitsedu.interpreter.models.scope.ScopeLocalCondition

data class OperationConditionIf(
    override val scope: ScopeLocalCondition,
    val value: OperationValue,
) : OperationCondition()
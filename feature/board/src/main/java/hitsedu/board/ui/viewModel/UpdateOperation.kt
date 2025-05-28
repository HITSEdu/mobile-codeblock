package hitsedu.board.ui.viewModel

import hitsedu.board.ui.utils.UpdateType
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.utils.copyScope

fun updateProjectWithScope(
    project: ProjectUIO?,
    parent: ScopeUIO,
    operation: OperationUIO,
    type: UpdateType,
): ProjectUIO? {
    if (project == null) return null
    val updatedGlobalScope = updateScope(project.globalScope, parent, operation, type)
    val updatedScopeUIOS = project.scopeUIOS.map { scope ->
        if (scope == parent) {
            updateScope(scope, parent, operation, type)
        } else scope
    }
    return project.copy(
        globalScope = updatedGlobalScope,
        scopeUIOS = updatedScopeUIOS
    )
}

private fun updateScope(
    scope: ScopeUIO,
    parent: ScopeUIO,
    operation: OperationUIO,
    type: UpdateType,
): ScopeUIO = if (scope == parent) {
    when (type) {
        UpdateType.ADD -> copyScope(scope, scope.operationUIOS + operation)
        UpdateType.DELETE -> copyScope(scope, scope.operationUIOS.filterNot { it == operation })
        UpdateType.REPLACE -> copyScope(scope, scope.operationUIOS.map {
            if (it.id == operation.id) operation else it
        })
    }
} else {
    copyScope(
        scope = scope,
        newOperationUIOS = scope.operationUIOS.map { o ->
            when (o) {
                is OperationIfUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                is OperationForUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                is OperationElseUIO -> o.copy(scope = updateScope(o.scope, parent, operation, type))
                else -> o
            }
        }
    )
}
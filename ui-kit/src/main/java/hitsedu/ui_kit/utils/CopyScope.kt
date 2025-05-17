package hitsedu.ui_kit.utils

import hitsedu.ui_kit.models.ScopeGlobalUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationUIO

fun copyScope(scope: ScopeUIO, newOperationUIOS: List<OperationUIO>): ScopeUIO {
    return when (scope) {
        is ScopeGlobalUIO -> scope.copy(operationUIOS = newOperationUIOS)
        else -> ScopeUIO(newOperationUIOS, scope.id)
    }
}
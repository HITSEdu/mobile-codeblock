package hitsedu.ui_kit.utils

import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.operation.OperationUIO

fun copyScope(scope: ScopeUIO, newOperationUIOS: List<OperationUIO>) = ScopeUIO(newOperationUIOS, scope.id)
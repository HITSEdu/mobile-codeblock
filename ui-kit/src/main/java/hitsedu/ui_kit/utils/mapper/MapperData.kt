package hitsedu.ui_kit.utils.mapper

import hitsedu.data.models.ProjectDBO
import hitsedu.data.models.ScopeDBO
import hitsedu.data.models.ValueDBO
import hitsedu.data.models.operation.OperationArrayDBO
import hitsedu.data.models.operation.OperationArrayIndexDBO
import hitsedu.data.models.operation.OperationDBO
import hitsedu.data.models.operation.OperationElseDBO
import hitsedu.data.models.operation.OperationForDBO
import hitsedu.data.models.operation.OperationIfDBO
import hitsedu.data.models.operation.OperationOutputDBO
import hitsedu.data.models.operation.OperationVariableDBO
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

fun ProjectUIO.toProjectDBO() = ProjectDBO(
    caption = caption,
    scale = scale,
    scopeDBOS = scopeUIOS.map { it.toScopeDBO() },
    globalScope = globalScope.toScopeDBO(),
    id = id,
)

fun ScopeUIO.toScopeDBO() = ScopeDBO(
    operationDBOS = operationUIOS.map { it.toOperationDBO() },
    id = id,
)

fun ValueUIO.toValueDBO() = ValueDBO(
    value = value,
    id = id,
)

fun OperationUIO.toOperationDBO(): OperationDBO = when (this) {
    is OperationVariableUIO -> OperationVariableDBO(
        id = id,
        name = name,
        valueDBO = value.toValueDBO(),
    )

    is OperationArrayUIO -> OperationArrayDBO(
        id = id,
        name = name,
        size = size,
        valueDBOS = values.map { it.toValueDBO() },
    )

    is OperationForUIO -> OperationForDBO(
        id = id,
        scopeDBO = scope.toScopeDBO(),
        variable = variable.toValueDBO(),
        condition = condition.toValueDBO(),
        valueDBO = value.toValueDBO(),
    )

    is OperationIfUIO -> OperationIfDBO(
        id = id,
        scopeDBO = scope.toScopeDBO(),
        valueDBO = value.toValueDBO(),
    )

    is OperationElseUIO -> OperationElseDBO(
        id = id,
        scopeDBO = scope.toScopeDBO(),
    )

    is OperationOutputUIO -> OperationOutputDBO(
        id = id,
        valueDBO = value.toValueDBO(),
    )

    is OperationArrayIndexUIO -> OperationArrayIndexDBO(
        id = id,
        name = name,
        index = index.toValueDBO(),
        valueDBO = value.toValueDBO(),
    )
}

fun ProjectDBO.toProjectUIO() = ProjectUIO(
    caption = caption,
    scale = scale,
    scopeUIOS = scopeDBOS.map { it.toScopeUIO() },
    globalScope = globalScope.toScopeUIO(),
    id = id,
)

fun ScopeDBO.toScopeUIO() = ScopeUIO(
    operationUIOS = operationDBOS.map { it.toOperationUIO() },
    id = id,
)

fun ValueDBO.toValueUIO() = ValueUIO(
    value = value,
    id = id,
)

fun OperationDBO.toOperationUIO(): OperationUIO = when (this) {
    is OperationVariableDBO -> OperationVariableUIO(
        name = name,
        value = valueDBO.toValueUIO(),
        id = id,
    )

    is OperationArrayDBO -> OperationArrayUIO(
        name = name,
        size = size,
        values = valueDBOS.map { it.toValueUIO() },
        id = id,
    )

    is OperationForDBO -> OperationForUIO(
        scope = scopeDBO.toScopeUIO(),
        variable = variable.toValueUIO(),
        condition = condition.toValueUIO(),
        value = valueDBO.toValueUIO(),
        id = id,
    )

    is OperationIfDBO -> OperationIfUIO(
        scope = scopeDBO.toScopeUIO(),
        value = valueDBO.toValueUIO(),
        id = id,
    )

    is OperationElseDBO -> OperationElseUIO(
        scope = scopeDBO.toScopeUIO(),
        id = id,
    )

    is OperationOutputDBO -> OperationOutputUIO(
        value = valueDBO.toValueUIO(),
        id = id,
    )

    is OperationArrayIndexDBO -> OperationArrayIndexUIO(
        name = name,
        index = index.toValueUIO(),
        value = valueDBO.toValueUIO(),
        id = id,
    )
}
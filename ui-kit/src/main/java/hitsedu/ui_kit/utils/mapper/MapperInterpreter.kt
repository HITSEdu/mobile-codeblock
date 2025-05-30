package hitsedu.ui_kit.utils.mapper

import hitsedu.interpreter.models.ConsoleOutput
import hitsedu.interpreter.models.E
import hitsedu.interpreter.models.Project
import hitsedu.interpreter.models.Scope
import hitsedu.interpreter.models.Value
import hitsedu.interpreter.models.operation.Operation
import hitsedu.interpreter.models.operation.OperationArray
import hitsedu.interpreter.models.operation.OperationArrayIndex
import hitsedu.interpreter.models.operation.OperationElse
import hitsedu.interpreter.models.operation.OperationFor
import hitsedu.interpreter.models.operation.OperationIf
import hitsedu.interpreter.models.operation.OperationOutput
import hitsedu.interpreter.models.operation.OperationVariable
import hitsedu.ui_kit.models.ProjectUIO
import hitsedu.ui_kit.models.ScopeUIO
import hitsedu.ui_kit.models.ValueUIO
import hitsedu.ui_kit.models.exception.ConsoleOutputUIO
import hitsedu.ui_kit.models.exception.EUIO
import hitsedu.ui_kit.models.operation.OperationArrayIndexUIO
import hitsedu.ui_kit.models.operation.OperationArrayUIO
import hitsedu.ui_kit.models.operation.OperationElseUIO
import hitsedu.ui_kit.models.operation.OperationForUIO
import hitsedu.ui_kit.models.operation.OperationIfUIO
import hitsedu.ui_kit.models.operation.OperationOutputUIO
import hitsedu.ui_kit.models.operation.OperationUIO
import hitsedu.ui_kit.models.operation.OperationVariableUIO

fun ProjectUIO.toProject() = Project(
    caption = caption,
    scale = scale,
    scopes = scopeUIOS.map { it.toScope() },
    globalScope = globalScope.toScope(),
    id = id,
)

fun ScopeUIO.toScope() = Scope(
    operations = operationUIOS.map { it.toOperation() },
    id = id,
)

fun ValueUIO.toValue() = Value(
    value = value,
    id = id,
)

fun OperationUIO.toOperation(): Operation = when (this) {
    is OperationVariableUIO -> OperationVariable(
        id = id,
        name = name,
        value = value.toValue(),
    )

    is OperationArrayUIO -> OperationArray(
        id = id,
        name = name,
        size = size,
        values = values.map { it.toValue() },
    )

    is OperationForUIO -> OperationFor(
        id = id,
        scope = scope.toScope(),
        variable = variable.toValue(),
        condition = condition.toValue(),
        value = value.toValue(),
    )

    is OperationIfUIO -> OperationIf(
        id = id,
        scope = scope.toScope(),
        value = value.toValue(),
    )

    is OperationElseUIO -> OperationElse(
        id = id,
        scope = scope.toScope(),
    )

    is OperationOutputUIO -> OperationOutput(
        id = id,
        value = value.toValue(),
    )

    is OperationArrayIndexUIO -> OperationArrayIndex(
        id = id,
        name = name,
        index = index.toValue(),
        value = value.toValue(),
    )
}

fun Project.toProjectUIO() = ProjectUIO(
    caption = caption,
    scale = scale,
    scopeUIOS = scopes.map { it.toScopeUIO() },
    globalScope = globalScope.toScopeUIO(),
    id = id,
)

fun Scope.toScopeUIO() = ScopeUIO(
    operationUIOS = operations.map { it.toOperationUIO() },
    id = id,
)

fun Value.toValueUIO() = ValueUIO(
    value = value,
    id = id,
)

fun Operation.toOperationUIO(): OperationUIO = when (this) {
    is OperationVariable -> OperationVariableUIO(
        name = name,
        value = value.toValueUIO(),
        id = id,
    )

    is OperationArray -> OperationArrayUIO(
        name = name,
        size = size,
        values = values.map { it.toValueUIO() },
        id = id,
    )

    is OperationFor -> OperationForUIO(
        scope = scope.toScopeUIO(),
        variable = variable.toValueUIO(),
        condition = condition.toValueUIO(),
        value = value.toValueUIO(),
        id = id,
    )

    is OperationIf -> OperationIfUIO(
        scope = scope.toScopeUIO(),
        value = value.toValueUIO(),
        id = id,
    )

    is OperationElse -> OperationElseUIO(
        scope = scope.toScopeUIO(),
        id = id,
    )

    is OperationOutput -> OperationOutputUIO(
        value = value.toValueUIO(),
        id = id,
    )

    is OperationArrayIndex -> OperationArrayIndexUIO(
        name = name,
        index = index.toValueUIO(),
        value = value.toValueUIO(),
        id = id,
    )
}

fun ConsoleOutput.toConsoleOutputUIO() = ConsoleOutputUIO(
    output = output,
    e = exception?.toEUIO()
)

fun E.toEUIO() = EUIO(
    message = message,
    blockId = blockId,
)

fun ConsoleOutputUIO.toConsoleOutput() = ConsoleOutput(
    output = output,
    exception = e?.toE()
)

fun EUIO.toE() = E(
    message = message,
    blockId = blockId,
)
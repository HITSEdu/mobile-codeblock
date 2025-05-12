package hitsedu.ui_kit.models.scope

import androidx.compose.ui.geometry.Offset
import hitsedu.ui_kit.models.operation.Operation

data class FunctionScope(
    override val operations: List<Operation>,
    override val childScopes: List<LocalScope>,
    val offset: Offset,
): LocalScope()
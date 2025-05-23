package hitsedu.board.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hitsedu.board.ui.BoardViewModel
import hitsedu.board.ui.components.elements.operation.ContainerOperation
import hitsedu.board.ui.utils.RenderOperation
import hitsedu.ui_kit.R
import hitsedu.ui_kit.models.ProjectUIO

@Composable
fun FunctionMain(
    project: ProjectUIO,
    viewModel: BoardViewModel,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.Center)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(24.dp),
                )
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(24.dp),
                )
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            project.globalScope.operationUIOS.forEach { it.RenderOperation(project.globalScope, viewModel) }
            Spacer(modifier = Modifier.height(4.dp))
            ContainerOperation(
                parentScope = project.globalScope,
                viewModel = viewModel,
            )
        }
        Row(
            modifier = Modifier
                .widthIn(64.dp, 128.dp)
                .height(24.dp)
                .offset(y = (-12).dp)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(24.dp),
                )
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(24.dp),
                )
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Main",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Button(
            onClick = {
                viewModel.run()
            },
            modifier = Modifier
                .widthIn(64.dp, 128.dp)
                .height(24.dp)
                .offset(y = 12.dp)
                .align(Alignment.BottomCenter)
                .padding(start = 12.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            border = BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.onPrimary,
            ),
            contentPadding = PaddingValues(2.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.run),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Icon(
                    painter = painterResource(R.drawable.icon_run),
                    contentDescription = "Run",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
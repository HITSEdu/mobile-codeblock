package hitsedu.ui_kit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hitsedu.ui_kit.R

@Composable
fun ProjectTitle(
    title: String,
    onTitleChange: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    var isEditing by remember { mutableStateOf(false) }
    var editableTitle by remember { mutableStateOf(title) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_left),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        TextField(
            value = editableTitle,
            onValueChange = {
                if (it.length <= 30 && it.isNotBlank())
                    editableTitle = it
            },
            singleLine = true,
            enabled = isEditing,
            readOnly = !isEditing,
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )

        IconButton(
            onClick = {
                if (isEditing) {
                    onTitleChange(editableTitle)
                }
                isEditing = !isEditing
            }
        ) {
            Icon(
                painter = painterResource(
                    id = if (isEditing)
                        R.drawable.icon_done else R.drawable.icon_edit
                ),
                contentDescription = "Edit",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

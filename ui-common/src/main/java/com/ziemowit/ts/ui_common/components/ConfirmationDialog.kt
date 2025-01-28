package com.ziemowit.ts.ui_common.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ziemowit.ts.ui_common.R

@Composable
fun ConfirmationDialog(
    title: String = stringResource(R.string.confirmation_dialog_title),
    message: String = stringResource(R.string.confirmation_dialog_message),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = stringResource(R.string.confirmation_dialog_confirm_button),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(R.string.confirmation_dialog_dismiss_button),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        shape = MaterialTheme.shapes.extraLarge, // Rounded corners (Material 3 uses extraLarge for dialogs)
        containerColor = MaterialTheme.colorScheme.surface,
        textContentColor = MaterialTheme.colorScheme.onSurface,
        iconContentColor = MaterialTheme.colorScheme.onSurface,
    )
}

@Preview
@Composable
private fun ConfirmationDialogPreview() {
    ConfirmationDialog(
        onConfirm = { },
        onDismiss = { },
    )
}

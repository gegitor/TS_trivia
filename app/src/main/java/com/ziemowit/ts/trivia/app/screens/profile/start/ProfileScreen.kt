package com.ziemowit.ts.trivia.app.screens.profile.start

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.ui_common.theme.TSTriviaTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState = ProfileUiState.stub(),
    interactions: ProfileInteractions = ProfileInteractions.STUB,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader(uiState, interactions)
        SettingsSection(interactions)
        Spacer(modifier = Modifier.weight(1f))
        LogoutButton(interactions)
    }

    EditNameDialog(uiState, interactions)
}

@Composable
fun ProfileHeader(uiState: ProfileUiState, interactions: ProfileInteractions) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(uiState.userInitials)
            Spacer(modifier = Modifier.width(16.dp))
            ProfileInfo(uiState.userName, interactions)
        }
    }
}

@Composable
fun Avatar(initials: String) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ProfileInfo(userName: String, interactions: ProfileInteractions) {
    Column {
        Text(
            text = stringResource(R.string.profile_welcome, userName),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = interactions.onEditNameClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.profile_edit_name))
        }
    }
}

@Composable
fun SettingsSection(interactions: ProfileInteractions) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.profile_settings),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            SettingsItem(
                title = stringResource(R.string.profile_account_settings),
                onClick = { interactions.onSettingsItemClicked(SettingsItem.ACCOUNT) }
            )

            SettingsItem(
                title = stringResource(R.string.profile_notification_preferences),
                onClick = { interactions.onSettingsItemClicked(SettingsItem.NOTIFICATIONS) }
            )

            SettingsItem(
                title = stringResource(R.string.profile_appearance),
                onClick = { interactions.onSettingsItemClicked(SettingsItem.APPEARANCE) }
            )

            SettingsItem(
                title = stringResource(R.string.profile_privacy),
                onClick = { interactions.onSettingsItemClicked(SettingsItem.PRIVACY) }
            )

            SettingsItem(
                title = stringResource(R.string.profile_help_feedback),
                onClick = { interactions.onSettingsItemClicked(SettingsItem.HELP) }
            )
        }
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun LogoutButton(interactions: ProfileInteractions) {
    Button(
        onClick = interactions.onLogoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(stringResource(R.string.profile_log_out))
    }
}

@Composable
fun EditNameDialog(uiState: ProfileUiState, interactions: ProfileInteractions) {
    if (uiState.isEditNameDialogVisible) {
        AlertDialog(
            onDismissRequest = interactions.onCancelEditClicked,
            title = { Text(stringResource(R.string.profile_edit_name_dialog_title)) },
            text = {
                OutlinedTextField(
                    value = uiState.newNameInput,
                    onValueChange = interactions.onNameInputChange,
                    label = { Text(stringResource(R.string.profile_edit_name_dialog_label)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = { interactions.onSaveNameClicked(uiState.newNameInput) }) {
                    Text(stringResource(R.string.profile_edit_name_dialog_save))
                }
            },
            dismissButton = {
                TextButton(onClick = interactions.onCancelEditClicked) {
                    Text(stringResource(R.string.profile_edit_name_dialog_cancel))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TSTriviaTheme {
        ProfileScreen()
    }
}

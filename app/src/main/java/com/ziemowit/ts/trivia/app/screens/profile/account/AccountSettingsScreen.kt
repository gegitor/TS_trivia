package com.ziemowit.ts.trivia.app.screens.profile.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ziemowit.ts.ui_common.theme.TSTriviaTheme

@Composable
fun AccountSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountSettingsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val interactions = viewModel.interactions

    AccountSettingsContent(
        modifier = modifier,
        uiState = uiState,
        interactions = interactions
    )
}

@Composable
fun AccountSettingsContent(
    modifier: Modifier = Modifier,
    uiState: AccountSettingsUiState = AccountSettingsUiState.STUB,
    interactions: AccountSettingsInteractions = AccountSettingsInteractions.STUB,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Account Settings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Personal Information Card
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Personal Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Email Section - Display Only
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = uiState.email,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (!uiState.isEmailVerified) {
                            Text(
                                text = "Not verified",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

                HorizontalDivider()

                // Password Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "••••••••",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    TextButton(onClick = interactions.onChangePasswordClick) {
                        Text("Change")
                    }
                }
            }
        }

        // Danger Zone Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Danger Zone",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )

                Button(
                    onClick = interactions.onDeleteAccountClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete Account")
                }
            }
        }
    }

    // Password Change Dialog
    if (uiState.isPasswordDialogVisible) {
        AlertDialog(
            onDismissRequest = interactions.onCancelDialogClick,
            title = { Text("Change Password") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.currentPasswordInput,
                        onValueChange = interactions.onCurrentPasswordInputChange,
                        label = { Text("Current Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = uiState.newPasswordInput,
                        onValueChange = interactions.onNewPasswordInputChange,
                        label = { Text("New Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        isError = uiState.passwordError != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = uiState.confirmPasswordInput,
                        onValueChange = interactions.onConfirmPasswordInputChange,
                        label = { Text("Confirm New Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        isError = uiState.passwordError != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (uiState.passwordError != null) {
                        Text(
                            text = uiState.passwordError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = interactions.onSavePasswordClick) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = interactions.onCancelDialogClick) {
                    Text("Cancel")
                }
            }
        )
    }

    // Delete Account Confirmation Dialog
    if (uiState.isDeleteAccountDialogVisible) {
        AlertDialog(
            onDismissRequest = interactions.onCancelDialogClick,
            title = { Text("Delete Account") },
            text = {
                Text("Are you sure you want to delete your account? This action cannot be undone and all your data will be permanently lost.")
            },
            confirmButton = {
                TextButton(
                    onClick = interactions.onConfirmDeleteAccount,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Yes, Delete My Account")
                }
            },
            dismissButton = {
                TextButton(onClick = interactions.onCancelDialogClick) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AccountSettingsScreenPreview() {
    TSTriviaTheme {
        AccountSettingsContent()
    }
}
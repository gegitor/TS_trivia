package com.ziemowit.ts.trivia.app.screens.profile.account

import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


/**
 * Created by Ziemowit Pazderski on 3/18/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
@HiltViewModel
class AccountSettingsViewModel @Inject constructor(routeNavigator: RouteNavigator) : ParentViewModel(routeNavigator) {
    private val _uiState = MutableStateFlow(AccountSettingsUiState())
    val uiState: StateFlow<AccountSettingsUiState> = _uiState.asStateFlow()

    val interactions = AccountSettingsInteractions(
        onChangePasswordClick = {
            _uiState.update {
                it.copy(
                    isPasswordDialogVisible = true,
                    currentPasswordInput = "",
                    newPasswordInput = "",
                    confirmPasswordInput = "",
                    passwordError = null
                )
            }
        },
        onSavePasswordClick = {
            // TODO password change use case with checks
            val currentState = _uiState.value
            if (currentState.newPasswordInput.length < 8) {
                _uiState.update {
                    it.copy(
                        passwordError = "Password must be at least 8 characters long"
                    )
                }
            } else if (currentState.newPasswordInput != currentState.confirmPasswordInput) {
                _uiState.update {
                    it.copy(
                        passwordError = "Passwords do not match"
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isPasswordDialogVisible = false,
                        passwordError = null
                    )
                }
                // TODO Here you would typically call a repository to update the password
            }
        },
        onCancelDialogClick = {
            _uiState.update {
                it.copy(
                    isPasswordDialogVisible = false,
                    isDeleteAccountDialogVisible = false,
                    passwordError = null
                )
            }
        },
        onCurrentPasswordInputChange = { input ->
            _uiState.update { it.copy(currentPasswordInput = input) }
        },
        onNewPasswordInputChange = { input ->
            _uiState.update { it.copy(newPasswordInput = input) }
        },
        onConfirmPasswordInputChange = { input ->
            _uiState.update { it.copy(confirmPasswordInput = input) }
        },
        onConfirmDeleteAccount = {
            // Here you would typically call a repository to delete the account
            _uiState.update { it.copy(isDeleteAccountDialogVisible = false) }
        }
    )
}

package com.ziemowit.ts.trivia.app.screens.profile.account


/**
 * Created by Ziemowit Pazderski on 3/29/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
data class AccountSettingsUiState(
    val email: String = "john.doe@example.com",
    val isEmailVerified: Boolean = true,
    val isPasswordDialogVisible: Boolean = false,
    val isDeleteAccountDialogVisible: Boolean = false,
    val currentPasswordInput: String = "",
    val newPasswordInput: String = "",
    val confirmPasswordInput: String = "",
    val passwordError: String? = null,
) {
    companion object {
        val STUB = AccountSettingsUiState(
            email = "preview@example.com",
            isEmailVerified = false,
            isPasswordDialogVisible = false,
            isDeleteAccountDialogVisible = false,
            currentPasswordInput = "currentPassword",
            newPasswordInput = "newPassword",
            confirmPasswordInput = "newPassword",
            passwordError = null
        )
    }
}
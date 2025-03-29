package com.ziemowit.ts.trivia.app.screens.profile.account


/**
 * Created by Ziemowit Pazderski on 3/29/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
data class AccountSettingsInteractions(
    val onChangePasswordClick: () -> Unit = {},
    val onSavePasswordClick: () -> Unit = {},
    val onCancelDialogClick: () -> Unit = {},
    val onCurrentPasswordInputChange: (String) -> Unit = {},
    val onNewPasswordInputChange: (String) -> Unit = {},
    val onConfirmPasswordInputChange: (String) -> Unit = {},
    val onToggleAccountLink: (String) -> Unit = {},
    val onDeleteAccountClick: () -> Unit = {},
    val onConfirmDeleteAccount: () -> Unit = {}
) {
    companion object {
        val STUB = AccountSettingsInteractions(
            onChangePasswordClick = {},
            onSavePasswordClick = {},
            onCancelDialogClick = {},
            onCurrentPasswordInputChange = {},
            onNewPasswordInputChange = {},
            onConfirmPasswordInputChange = {},
            onToggleAccountLink = {},
            onDeleteAccountClick = {},
            onConfirmDeleteAccount = {}
        )
    }
}
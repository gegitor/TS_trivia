package com.ziemowit.ts.trivia.app.screens.profile.start

/**
 * Created by Ziemowit Pazderski on 3/17/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
data class ProfileUiState(
    val userName: String,
    val userInitials: String,
    val isEditNameDialogVisible: Boolean,
    val newNameInput: String,
    val isDarkMode: Boolean,
) {
    companion object {
        fun stub(
            userName: String = "John",
            userInitials: String = "JD",
            isEditNameDialogVisible: Boolean = false,
            newNameInput: String = "",
            isDarkMode: Boolean = false
        ) = ProfileUiState(
            userName = userName,
            userInitials = userInitials,
            isEditNameDialogVisible = isEditNameDialogVisible,
            newNameInput = newNameInput,
            isDarkMode = isDarkMode,
        )
    }
}

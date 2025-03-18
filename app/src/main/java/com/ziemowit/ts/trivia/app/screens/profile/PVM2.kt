package com.ziemowit.ts.trivia.app.screens.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by Ziemowit Pazderski on 3/17/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
private class PVM2 : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState2())
    val uiState: StateFlow<ProfileUiState2> = _uiState.asStateFlow()

    val interactions = ProfileInteractions2(
        onEditNameClick = {
            _uiState.update {
                it.copy(
                    isEditNameDialogVisible = true,
                    newNameInput = it.userName
                )
            }
        },
        onSaveNameClick = { newName ->
            if (newName.isNotBlank()) {
                _uiState.update {
                    it.copy(
                        userName = newName,
                        userInitials = generateInitials(newName),
                        isEditNameDialogVisible = false
                    )
                }
            }
        },
        onCancelEditClick = {
            _uiState.update { it.copy(isEditNameDialogVisible = false) }
        },
        onNameInputChange = { input ->
            _uiState.update { it.copy(newNameInput = input) }
        },
        onSettingsItemClick = { item ->
            // Handle navigation to different settings screens
            println("Navigate to ${item.name} settings")
        },
        onLogoutClick = {
            // Handle logout logic
            println("User logged out")
        },
        onBackClick = {
            // Handle back navigation
            println("Navigate back")
        }
    )

    private fun generateInitials(name: String): String {
        return name.split(" ")
            .take(2)
            .mapNotNull { it.firstOrNull()?.uppercase() }
            .joinToString("")
            .take(2)
    }
}

private data class ProfileUiState2(
    val userName: String = "John",
    val userInitials: String = "JD",
    val isEditNameDialogVisible: Boolean = false,
    val newNameInput: String = "",
    val isDarkMode: Boolean = false
)

// Data class for Profile UI Interactions
private data class ProfileInteractions2(
    val onBackClick: () -> Unit = {},
    val onEditNameClick: () -> Unit = {},
    val onSaveNameClick: (String) -> Unit = {},
    val onCancelEditClick: () -> Unit = {},
    val onNameInputChange: (String) -> Unit = {},
    val onSettingsItemClick: (SettingsItem) -> Unit = {},
    val onLogoutClick: () -> Unit = {}
)

// Enum for Settings Items
private enum class SettingsItem2 {
    ACCOUNT, NOTIFICATIONS, APPEARANCE, PRIVACY, HELP
}

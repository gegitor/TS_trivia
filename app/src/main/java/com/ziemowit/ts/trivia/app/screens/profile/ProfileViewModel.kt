package com.ziemowit.ts.trivia.app.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ziemowit Pazderski on 3/17/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(routeNavigator: RouteNavigator, preferencesRepository: PreferencesRepository) :
    ParentViewModel(routeNavigator) {

    private val userName = preferencesRepository.getUserName() ?: ""
    private val userInitials = generateInitials(userName)

    private val _uiState = mutableStateOf(ProfileUiState(
        userName = userName,
        userInitials = userInitials,
        isEditNameDialogVisible = false,
        newNameInput = "",
        isDarkMode = false,
    ))
    val uiState: State<ProfileUiState> = _uiState

    val interactions = ProfileInteractions(
        onEditNameClicked = {
            _uiState.value = _uiState.value.copy(
                isEditNameDialogVisible = true,
                newNameInput = _uiState.value.userName
            )
        },
        onSaveNameClicked = { newName ->
            if (newName.isNotBlank()) {
                _uiState.value = _uiState.value.copy(
                    userName = newName,
                    userInitials = generateInitials(newName),
                    isEditNameDialogVisible = false,
                )
            }
        },
        onCancelEditClicked = {
            _uiState.value = _uiState.value.copy(isEditNameDialogVisible = false)
        },
        onNameInputChange = { input ->
            _uiState.value = _uiState.value.copy(newNameInput = input)
        },
        onSettingsItemClicked = { item ->
            // TODO Handle navigation to different settings screens
            println("Navigate to ${item.name} settings")
        },
        onLogoutClick = {
            // TODO Handle logout logic
            println("User logged out")
        },
        onBackClicked = {
            routeNavigator.navigateUp()
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


// Enum for Settings Items
enum class SettingsItem {
    ACCOUNT, NOTIFICATIONS, APPEARANCE, PRIVACY, HELP
}

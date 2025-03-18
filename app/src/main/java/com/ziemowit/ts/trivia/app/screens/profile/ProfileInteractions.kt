package com.ziemowit.ts.trivia.app.screens.profile


/**
 * Created by Ziemowit Pazderski on 3/17/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */

data class ProfileInteractions(
    val onBackClicked: () -> Unit = {},
    val onEditNameClicked: () -> Unit = {},
    val onEditAvatarClicked: () -> Unit = {},
    val onSaveNameClicked: (String) -> Unit = {},
    val onCancelEditClicked: () -> Unit = {},
    val onNameInputChange: (String) -> Unit = {},
    val onSettingsItemClicked: (SettingsItem) -> Unit = {},
    val onLogoutClick: () -> Unit = {}
){
    companion object {
        val STUB = ProfileInteractions(
            onBackClicked = {},
            onEditNameClicked = {},
            onEditAvatarClicked = {},
            onSaveNameClicked = {},
            onCancelEditClicked = {},
            onNameInputChange = {},
            onSettingsItemClicked = {},
            onLogoutClick = {},
        )
    }
}
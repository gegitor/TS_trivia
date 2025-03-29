package com.ziemowit.ts.trivia.app.screens.profile.start

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

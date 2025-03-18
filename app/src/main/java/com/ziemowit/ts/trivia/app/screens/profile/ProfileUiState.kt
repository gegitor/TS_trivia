package com.ziemowit.ts.trivia.app.screens.profile

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

//data class ProfileUiState(
//    val userName: State<String>,
//    val userInitials: State<String>,
//    val isEditNameDialogVisible: State<Boolean>,
//    val newNameInput: State<String>,
//    val isDarkMode: State<Boolean>
//) {
//    companion object {
//        fun stub(
//            userName: String = "John",
//            userInitials: String = "JD",
//            isEditNameDialogVisible: Boolean = false,
//            newNameInput: String = "",
//            isDarkMode: Boolean = false
//        ) = ProfileUiState(
//            userName = mutableStateOf(userName),
//            userInitials = mutableStateOf(userInitials),
//            isEditNameDialogVisible = mutableStateOf(isEditNameDialogVisible),
//            newNameInput = mutableStateOf(newNameInput),
//            isDarkMode = mutableStateOf(isDarkMode),
//        )
//    }
//}

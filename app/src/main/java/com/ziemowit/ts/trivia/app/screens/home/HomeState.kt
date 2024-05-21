package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

internal data class HomeState(
    val email: State<String>,
) {
    companion object {
        fun stub(
            email: String = "john.smith@gmail.com",
        ) = HomeState(
            email = mutableStateOf(email),
        )
    }
}
package com.ziemowit.ts.trivia.app.screens.home

internal data class HomeScreenInteractions(
    val onBackClicked: () -> Unit,
    val onNavigateToQuizInit: () -> Unit,
) {
    companion object {
        val STUB = HomeScreenInteractions(
            onBackClicked = {},
            onNavigateToQuizInit = {},
        )
    }
}
package com.ziemowit.ts.trivia.app.screens.home

internal data class HomeScreenInteractions(
    val onBackClicked: () -> Unit,
    val onNavigateToQuiz: () -> Unit,
//    val onDifficultySelected: () -> Unit, //TODO
) {
    companion object {
        val STUB = HomeScreenInteractions(
            onBackClicked = {},
            onNavigateToQuiz = {},
        )
    }
}
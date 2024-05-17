package com.ziemowit.ts.trivia.app.screens.quiz

internal data class QuizScreenInteractions(
    val onBackClicked: () -> Unit,
) {
    companion object {
        val STUB = QuizScreenInteractions(
            onBackClicked = {},
        )
    }
}
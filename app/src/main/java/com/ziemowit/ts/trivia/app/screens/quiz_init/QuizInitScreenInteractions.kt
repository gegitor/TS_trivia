package com.ziemowit.ts.trivia.app.screens.quiz_init

internal data class QuizInitScreenInteractions(
    val onBackClicked: () -> Unit,
    val onNavigateToQuiz: () -> Unit,
//    val onDifficultySelected: () -> Unit, //TODO
) {
    companion object {
        val STUB = QuizInitScreenInteractions(
            onBackClicked = {},
            onNavigateToQuiz = {},
        )
    }
}
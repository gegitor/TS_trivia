package com.ziemowit.ts.trivia.app.screens.quiz_init

import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty

internal data class QuizInitScreenInteractions(
    val onBackClicked: () -> Unit,
    val onNavigateToQuiz: (difficulty: Difficulty) -> Unit,
//    val onDifficultySelected: () -> Unit, //TODO
) {
    companion object {
        val STUB = QuizInitScreenInteractions(
            onBackClicked = {},
            onNavigateToQuiz = {},
        )
    }
}
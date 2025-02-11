package com.ziemowit.ts.trivia.app.screens.quiz_init

import com.ziemowit.ts.trivia.data.model.Difficulty


internal data class QuizInitScreenInteractions(
    val onBackClicked: () -> Unit,
    val onNavigateToQuiz: (difficulty: Difficulty) -> Unit,
    val setHiddenDifficultyVisibility: (visible: Boolean) -> Unit,
) {
    companion object {
        val STUB = QuizInitScreenInteractions(
            onBackClicked = {},
            onNavigateToQuiz = {},
            setHiddenDifficultyVisibility = {},
        )
    }
}
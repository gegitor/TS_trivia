package com.ziemowit.ts.trivia.app.screens.quiz

import com.ziemowit.ts.trivia.data.PotentialAnswer

internal data class QuizScreenInteractions(
    val onBackClicked: () -> Unit,
    val onAnswerSelected: (Int, PotentialAnswer) -> Unit,
    val onNextQuestion: () -> Unit,
    val onQuizFinished: () -> Unit,
) {
    companion object {
        val STUB = QuizScreenInteractions(
            onBackClicked = {},
            onAnswerSelected = { _, _ -> },
            onNextQuestion = {},
            onQuizFinished = {},
        )
    }
}

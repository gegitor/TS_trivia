package com.ziemowit.ts.trivia.app.screens.quiz

import com.ziemowit.ts.trivia.data.model.PotentialAnswer

internal data class QuizScreenInteractions(
    val onBackClicked: () -> Unit,
    val onAnswerSelected: (Int, PotentialAnswer) -> Unit,
    val onQuizFinished: () -> Unit,
    val onConfirmBack: () -> Unit,
    val onDismissDialog: () -> Unit,
) {
    companion object {
        val STUB = QuizScreenInteractions(
            onBackClicked = {},
            onAnswerSelected = { _, _ -> },
            onQuizFinished = {},
            onConfirmBack = {},
            onDismissDialog = {},
        )
    }
}

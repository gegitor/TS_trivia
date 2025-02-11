package com.ziemowit.ts.trivia.app.screens.quiz_summary


data class QuizSummaryInteractions(
    val onPlayAgain: () -> Unit,
    val onReviewAnswers: () -> Unit
)  {
    companion object {
        val STUB = QuizSummaryInteractions(
            onPlayAgain = {},
            onReviewAnswers = {},
        )
    }
}
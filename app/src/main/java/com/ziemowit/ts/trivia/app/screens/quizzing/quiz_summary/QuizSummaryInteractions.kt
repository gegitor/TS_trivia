package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary

import android.content.Context


data class QuizSummaryInteractions(
    val onPlayAgain: () -> Unit,
    val onMainMenu: () -> Unit,
    val onShareScore: (context: Context) -> Unit
)  {
    companion object {
        val STUB = QuizSummaryInteractions(
            onPlayAgain = {},
            onMainMenu = {},
            onShareScore = {}
        )
    }
}

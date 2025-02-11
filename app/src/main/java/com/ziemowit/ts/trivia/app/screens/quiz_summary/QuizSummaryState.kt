package com.ziemowit.ts.trivia.app.screens.quiz_summary


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class QuizSummaryState(
    val difficulty: State<String>,
    val score: State<String>,
    val leaderRankings: State<List<String>>,
    val userRank: State<String>
) {
    companion object {
        fun stub(
            difficulty: String = "Easy",
            score: String = "0/0",
            leaderRankings: List<String> = emptyList(),
            userRank: String = "Unranked",
        ) = QuizSummaryState(
            difficulty = mutableStateOf(difficulty),
            score = mutableStateOf(score),
            leaderRankings = mutableStateOf(leaderRankings),
            userRank = mutableStateOf(userRank),
        )
    }
}
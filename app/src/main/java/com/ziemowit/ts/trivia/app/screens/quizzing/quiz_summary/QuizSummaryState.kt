package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.domain.model.Difficulty

data class QuizSummaryState(
    val difficulty: State<Difficulty>,
    val score: State<String>,
    val leaderRankings: State<List<String>>,
    val userRank: State<String>
) {
    companion object {
        fun stub(
            difficulty: Difficulty = Difficulty.EASY,
            score: String = "3/10",
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
package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.data.Difficulty

internal data class QuizState(
    val difficulty: State<Difficulty>,
) {
    companion object {
        fun stub(
            difficulty: Difficulty = Difficulty.EASY,
        ) = QuizState(
            difficulty = mutableStateOf(difficulty),
        )
    }
}

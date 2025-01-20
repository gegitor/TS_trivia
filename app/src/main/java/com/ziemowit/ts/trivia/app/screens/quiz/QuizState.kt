package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.R

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


enum class Difficulty(@StringRes val diffText: Int, val value: Int) {
    EASY(R.string.diff_easy, 0),
    MEDIUM(R.string.diff_medium, 1),
    HARD(R.string.diff_hard, 2),
    WOJTEK(R.string.diff_wojtek, 3);

    companion object {
        fun fromOrdinal(ordinal: Int) = entries.getOrNull(ordinal) ?: EASY
    }
}
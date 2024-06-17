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


enum class Difficulty(@StringRes val diffText: Int) {
    EASY(R.string.diff_easy), MEDIUM(R.string.diff_medium), HARD(R.string.diff_hard), WOJTEK(R.string.diff_wojtek);

    companion object {
        fun fromOrdinal(ordinal: Int) = entries.getOrNull(ordinal) ?: EASY
    }
}
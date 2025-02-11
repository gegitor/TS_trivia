package com.ziemowit.ts.trivia.data.model

import androidx.annotation.StringRes
import com.ziemowit.ts.trivia.R

enum class Difficulty(val index: Int, @StringRes val displayName: Int) {
    EASY(0, R.string.diff_easy),
    MEDIUM(1, R.string.diff_medium),
    HARD(2, R.string.diff_hard),
    WOJTEK(3, R.string.diff_wojtek);

    companion object {
        fun fromOrdinal(ordinal: Int) = entries.getOrNull(ordinal) ?: EASY
    }
}

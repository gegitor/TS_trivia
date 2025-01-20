package com.ziemowit.ts.trivia.data

import androidx.annotation.StringRes
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty

data class Question(
    val category: Category,
    val difficulty: Difficulty,
    val answer: String,
    val wrongAnswers: List<String>,
)

enum class Category(val index: Int, @StringRes displayName: Int) {
    GAME_MECHANICS(0, R.string.category_game_mechanics),
    CARDS_OPERATION(1, R.string.category_cards_operation),
    CARDS_HISTORY(2, R.string.category_cards_history),
    CREATORS(3, R.string.category_creators),
    PLAYERS(4, R.string.category_players),
    GAME_HISTORY(5, R.string.category_game_history),
    BOARD(6, R.string.category_board);
}

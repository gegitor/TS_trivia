package com.ziemowit.ts.trivia.data

data class Question(
    val index: Int,
    val category: Category,
    val difficulty: Difficulty,
    val answer: String,
    val wrongAnswers: List<String>,
)

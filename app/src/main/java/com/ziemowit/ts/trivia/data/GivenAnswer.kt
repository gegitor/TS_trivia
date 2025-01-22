package com.ziemowit.ts.trivia.data

data class GivenAnswer(
    val questionIndex: Int,
    val answerText: String,
    val correct: Boolean,
)

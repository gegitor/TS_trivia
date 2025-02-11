package com.ziemowit.ts.trivia.data.model

data class GivenAnswer(
    val questionIndex: Int,
    val answerText: String,
    val correct: Boolean,
)

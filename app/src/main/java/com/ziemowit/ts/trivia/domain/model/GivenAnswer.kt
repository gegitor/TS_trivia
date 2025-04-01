package com.ziemowit.ts.trivia.domain.model

data class GivenAnswer(
    val questionIndex: Int,
    val answerText: String,
    val correct: Boolean,
)

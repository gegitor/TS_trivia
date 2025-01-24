package com.ziemowit.ts.trivia.data

data class PotentialAnswer(
    val answerText: String,
    val correct: Boolean,
)

val emptyPotentialAnswer = PotentialAnswer("answer", false)

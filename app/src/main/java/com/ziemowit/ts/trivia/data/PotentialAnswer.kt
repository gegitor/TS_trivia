package com.ziemowit.ts.trivia.data

data class PotentialAnswer(
    val answerText: String,
    val correct: Boolean,
    val selected: Boolean,
)

val emptyPotentialAnswer = PotentialAnswer("answer", false, false)

package com.ziemowit.ts.trivia.data.model

data class PotentialAnswer(
    val answerText: String,
    val correct: Boolean,
    val selected: Boolean,
)

val emptyPotentialAnswer = PotentialAnswer("answer", false, false)

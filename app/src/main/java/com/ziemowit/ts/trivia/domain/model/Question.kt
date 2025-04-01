package com.ziemowit.ts.trivia.domain.model

//Question data used to display it
data class Question(
    val index: Int,
//    val category: Category,
    val difficulty: Difficulty,
    val questionText: String,
    val potentialAnswers: List<PotentialAnswer>,
)

val emptyQuestion = Question(
    0,
    Difficulty.EASY,
    "q",
    listOf(emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer)
)
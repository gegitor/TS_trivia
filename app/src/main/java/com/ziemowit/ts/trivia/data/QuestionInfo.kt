package com.ziemowit.ts.trivia.data

//Question data used to display it
data class QuestionInfo(
    val index: Int,
//    val category: Category,
    val difficulty: Difficulty,
    val questionText: String,
    val potentialAnswers: List<PotentialAnswer>,
)

val emptyQuestionInfo = QuestionInfo(
    0,
    Difficulty.EASY,
    "q",
    listOf(emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer, emptyPotentialAnswer)
)
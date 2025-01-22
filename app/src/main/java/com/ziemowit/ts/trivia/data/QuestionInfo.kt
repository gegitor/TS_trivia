package com.ziemowit.ts.trivia.data

//Question data used to display it
data class QuestionInfo(
    val index: Int,
//    val category: Category,
    val difficulty: Difficulty,
    val question: String,
    val potentialAnswers: List<PotentialAnswer>,
)

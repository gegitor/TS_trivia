package com.ziemowit.ts.trivia.data.model.file

data class QuestionFile(
    val index: Int,
    val category: CategoryFile,
    val difficultyFile: DifficultyFile,
    val question: String,
    val correctAnswer: String,
    val wrongAnswers: List<String>,
)

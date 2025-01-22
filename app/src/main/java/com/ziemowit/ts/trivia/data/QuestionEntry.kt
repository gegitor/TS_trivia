package com.ziemowit.ts.trivia.data

//Question as stored in csv
data class QuestionEntry(
    val index: Int,
    val category: Category,
    val difficulty: Difficulty,
    val question: String,
    val correctAnswer: String,
    val wrongAnswers: List<String>,
)

fun QuestionEntry.toQuestionInfo(): QuestionInfo {

    val answers: MutableList<PotentialAnswer> = wrongAnswers.map { answerText ->
        PotentialAnswer(answerText, false)
    }.toMutableList()

    answers.add(PotentialAnswer(correctAnswer, true))
    answers.shuffle()
    return QuestionInfo(index, difficulty, question, answers.toList())
}
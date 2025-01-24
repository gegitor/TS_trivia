package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.data.Difficulty
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.trivia.data.emptyQuestionInfo

internal data class QuizState(
    val difficulty: State<Difficulty>,
    val isAnswerSelected: State<Boolean>,
    val isAnswerEnabled: State<Boolean>,
    val questionCount: State<String>,
    val question: State<QuestionInfo>,
) {
    companion object {
        fun stub(
            difficulty: Difficulty = Difficulty.EASY,
            isAnswerSelected: Boolean = false,
            isAnswerEnabled: Boolean = true,
            questionCount: String = "Question 3/4",
            question: QuestionInfo = emptyQuestionInfo,
        ) = QuizState(
            difficulty = mutableStateOf(difficulty),
            isAnswerSelected = mutableStateOf(isAnswerSelected),
            isAnswerEnabled = mutableStateOf(isAnswerEnabled),
            questionCount = mutableStateOf(questionCount),
            question = mutableStateOf(question),
        )
    }
}

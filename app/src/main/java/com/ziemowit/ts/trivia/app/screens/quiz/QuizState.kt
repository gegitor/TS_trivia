package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.data.Difficulty
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.trivia.data.emptyQuestionInfo

internal data class QuizState(
    val difficulty: State<Difficulty>,
    val isAnswerEnabled: State<Boolean>,
    val questionCount: State<String>,
    val isLoading: State<Boolean>,
    val question: State<QuestionInfo>,
) {
    companion object {
        fun stub(
            difficulty: Difficulty = Difficulty.EASY,
            isAnswerEnabled: Boolean = false,
            questionCount: String = "Question 3/4",
            isLoading: Boolean = false,
            question: QuestionInfo = emptyQuestionInfo,
        ) = QuizState(
            difficulty = mutableStateOf(difficulty),
            isAnswerEnabled = mutableStateOf(isAnswerEnabled),
            questionCount = mutableStateOf(questionCount),
            isLoading = mutableStateOf(isLoading),
            question = mutableStateOf(question),
        )
    }
}

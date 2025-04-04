package com.ziemowit.ts.trivia.app.screens.quizzing.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.domain.model.Question
import com.ziemowit.ts.trivia.domain.model.emptyQuestion

internal data class QuizState(
    val difficulty: State<String>,
    val isAnswerEnabled: State<Boolean>,
    val questionCount: State<String>,
    val question: State<Question>,
    val showConfirmationDialog: State<Boolean>,
) {
    companion object {
        fun stub(
            difficulty: String = "Easy",
            isAnswerEnabled: Boolean = false,
            questionCount: String = "Question 3/4",
            question: Question = emptyQuestion,
            showConfirmationDialog: Boolean = false
        ) = QuizState(
            difficulty = mutableStateOf(difficulty),
            isAnswerEnabled = mutableStateOf(isAnswerEnabled),
            questionCount = mutableStateOf(questionCount),
            question = mutableStateOf(question),
            showConfirmationDialog = mutableStateOf(showConfirmationDialog),
        )
    }
}


sealed interface QuizLoadingState {
    companion object {
        internal fun stub(
            quizState: QuizState = QuizState.stub(),
        ) = QuizReady(
            quizState = quizState
        )
    }
}

internal data object QuizLoading: QuizLoadingState
internal class QuizReady(val quizState: QuizState): QuizLoadingState
internal class QuizLoadError(val error: Throwable): QuizLoadingState //TODO

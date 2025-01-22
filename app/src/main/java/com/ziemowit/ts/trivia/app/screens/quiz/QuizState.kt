package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.data.Difficulty
import com.ziemowit.ts.trivia.data.QuestionInfo

internal data class QuizState(
    val difficulty: State<Difficulty>,
    val currentQuestionIndex: State<Int>,
    val questions: State<List<QuestionInfo>>,
) {
    companion object {
        fun stub(
            difficulty: Difficulty = Difficulty.EASY,
            currentQuestionIndex: Int = 0,
            questions: List<QuestionInfo> = emptyList(),
        ) = QuizState(
            difficulty = mutableStateOf(difficulty),
            currentQuestionIndex = mutableIntStateOf(currentQuestionIndex),
            questions = mutableStateOf(questions),
        )
    }
}

package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.data.GivenAnswer
import com.ziemowit.ts.trivia.data.PotentialAnswer
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.toQuestionInfo
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    routeNavigator: RouteNavigator,
    private val questionRepository: QuestionRepository,
) : ParentViewModel(routeNavigator) {

    private val quizArgs = QuizArgs(savedStateHandle)
    private val difficulty = mutableStateOf(quizArgs.difficulty)
    private val questions = mutableStateOf<List<QuestionInfo>>(emptyList())
    private val currentQuestionIndex = mutableIntStateOf(0)    
    private val isAnswerSelected = mutableStateOf(false)

    private val userAnswers = mutableListOf<GivenAnswer>()

    init {
        Timber.d("QuizViewModel init diff: ${difficulty.value}")
        loadQuestions()
    }

    internal val state = QuizState(
        difficulty = difficulty,
        currentQuestionIndex = currentQuestionIndex,
        isAnswerSelected = isAnswerSelected,
        questions = questions,
    )

    internal val interactions = QuizScreenInteractions(
        onBackClicked = ::onBackClicked,
        onAnswerSelected = ::onAnswerSelected,
        onNextQuestion = ::onNextQuestion,
        onQuizFinished = ::onQuizFinished,
    )

    private fun onQuizFinished() {
        Timber.d("ZZZ onQuizFinished")
        //TODO
        // Send the score (only) to the next screen
        // Store the answers in the database (later) on the go maybe?
//        navigateToRoute(QuizRoute.getRoute(difficulty))
    }


    private fun onAnswerSelected(questionIndex: Int, potentialAnswer: PotentialAnswer) {
        viewModelScope.launch {
            delay(1000L)
            Timber.d("ZZZ onAnswerSelected, questionIndex: $questionIndex, potentialAnswer: $potentialAnswer")            
            isAnswerSelected.value = true
            userAnswers.add(GivenAnswer(questionIndex, potentialAnswer.answerText, potentialAnswer.correct))
        }
    }

    private fun onNextQuestion() = viewModelScope.launch {
        isAnswerSelected.value = false
        delay(500L)
        if (currentQuestionIndex.intValue < questions.value.lastIndex) {
            currentQuestionIndex.intValue += 1
        } else {
            onQuizFinished()
        }
    }

    private fun loadQuestions() = viewModelScope.launch {
        val dbQuestions = questionRepository.getQuestions(difficulty.value)
        Timber.d("Loaded questions: $questions")
        //TODO - choose a subset of questions from the full list
        questions.value = dbQuestions.map { it.toQuestionInfo() }
    }

    private fun calculateScore(): Int {
        var score = 0
        userAnswers.forEach { answer ->
            if (answer.correct) score++
        }
        return score
    }
}

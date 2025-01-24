package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.data.GivenAnswer
import com.ziemowit.ts.trivia.data.PotentialAnswer
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.emptyQuestionInfo
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
    private lateinit var questions: List<QuestionInfo>
    private var currentQuestionIndex = 0
    private val userAnswers = mutableListOf<GivenAnswer>()

    // State objects
    private val difficulty = mutableStateOf(quizArgs.difficulty)
    private val questionCount = mutableStateOf("")
    private val question = mutableStateOf(emptyQuestionInfo)
    private val isAnswerSelected = mutableStateOf(false)
    private val isAnswerEnabled = mutableStateOf(true)


    init {
        Timber.d("QuizViewModel init diff: ${difficulty.value}")
        loadQuestions()
    }

    internal val state = QuizState(
        difficulty = difficulty,
        isAnswerChosen = isAnswerSelected,
        isAnswerEnabled = isAnswerEnabled,
        questionCount = questionCount,
        question = question,
    )

    internal val interactions = QuizScreenInteractions(
        onBackClicked = ::onBackClicked,
        onAnswerSelected = ::onAnswerSelected,
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
            isAnswerEnabled.value = false
            Timber.d("ZZZ onAnswerSelected, questionIndex: $questionIndex, potentialAnswer: $potentialAnswer")
            userAnswers.add(GivenAnswer(questionIndex, potentialAnswer.answerText, potentialAnswer.correct))
            isAnswerSelected.value = true
            delay(1000L)
            if (currentQuestionIndex < questions.lastIndex) {
                currentQuestionIndex++
                question.value = questions[currentQuestionIndex]
                questionCount.value = "Question ${currentQuestionIndex}/${questions.size}"
            } else {
                onQuizFinished()
            }
         }
    }


    private fun loadQuestions() = viewModelScope.launch {
        val dbQuestions = questionRepository.getQuestions(difficulty.value)
        Timber.d("Loaded questions: $dbQuestions")
        //TODO - choose a subset of questions from the full list
        questions = dbQuestions.map { it.toQuestionInfo() }
        question.value = questions[currentQuestionIndex]
        questionCount.value = "Question 1/${questions.size}"
    }

    private fun calculateScore(): Int {
        var score = 0
        userAnswers.forEach { answer ->
            if (answer.correct) score++
        }
        return score
    }
}

package com.ziemowit.ts.trivia.app.screens.quiz

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.data.GivenAnswer
import com.ziemowit.ts.trivia.data.PotentialAnswer
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.emptyQuestionInfo
import com.ziemowit.ts.trivia.data.toQuestionInfo
import com.ziemowit.ts.trivia.nav.RouteNavigator
import com.ziemowit.ts.ui_common.components.ConfirmationDialogOwner
import com.ziemowit.ts.ui_common.components.ConfirmationDialogOwnerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
    routeNavigator: RouteNavigator,
    private val questionRepository: QuestionRepository,
) : ParentViewModel(routeNavigator),
    ConfirmationDialogOwner by ConfirmationDialogOwnerImpl() {

    private val quizArgs = QuizArgs(savedStateHandle)
    private var questions: List<QuestionInfo> = emptyList()
    private val currentQuestionIndex = mutableIntStateOf(0)
    private val userAnswers = mutableListOf<GivenAnswer>()

    // State objects
    private val difficulty = mutableStateOf(quizArgs.difficulty)
    private val question = mutableStateOf(emptyQuestionInfo)
    private val isAnswerEnabled = mutableStateOf(true)
    private val questionCount: MutableState<String> = mutableStateOf("")


    init {
        Timber.d("QuizViewModel init diff: ${difficulty.value}")
        loadQuestions()
    }

    private val state = QuizState(
        difficulty = difficulty,
        isAnswerEnabled = isAnswerEnabled,
        questionCount = questionCount,
        question = question,
        showConfirmationDialog = showConfirmationDialog
    )

    internal val loadingState: MutableState<QuizLoadingState> = mutableStateOf(QuizLoading)

    internal val interactions = QuizScreenInteractions(
        onBackClicked = ::onBackClicked,
        onAnswerSelected = ::onAnswerSelected,
        onQuizFinished = ::onQuizFinished,
        onConfirmBack = ::onConfirmBackFromInterface,
        onDismissDialog = ::onDismissDialog,
    )

    private fun onConfirmBackFromInterface() {
        Timber.d("ZZZ onConfirmBackFromInterface")
        onConfirmBack()
        onBack()
    }

    private fun onQuizFinished() {
        Timber.d("onQuizFinished")
        //TODO
        // Send the score (only) to the next screen
        // Store the answers in the database (later) on the go maybe?
//        navigateToRoute(QuizRoute.getRoute(difficulty))
    }

    private fun onAnswerSelected(questionIndex: Int, potentialAnswer: PotentialAnswer) {
        viewModelScope.launch {
            isAnswerEnabled.value = false
            Timber.d("onAnswerSelected, questionIndex: $questionIndex, potentialAnswer: $potentialAnswer")
            userAnswers.add(GivenAnswer(questionIndex, potentialAnswer.answerText, potentialAnswer.correct))

            // Update the question object with the selected answer
            question.value = question.value.copy(
                potentialAnswers = question.value.potentialAnswers.map {
                    if (it.answerText == potentialAnswer.answerText) {
                        it.copy(selected = true)
                    } else {
                        it
                    }
                }
            )

            delay(1000L)
            if (currentQuestionIndex.intValue < questions.lastIndex) {
                currentQuestionIndex.intValue++
                nextQuestion()
            } else {
                onQuizFinished()
            }
        }
    }

    private fun nextQuestion() {
        question.value = questions[currentQuestionIndex.intValue]
        isAnswerEnabled.value = true
        questionCount.value =
            context.getString(R.string.question_count, currentQuestionIndex.intValue + 1, questions.size)
    }

    private fun loadQuestions() = viewModelScope.launch {
        val dbQuestions = questionRepository.getQuestions(difficulty.value)
        Timber.d("Loaded questions: $dbQuestions")
        //TODO - choose a subset of questions from the full list
        questions = dbQuestions.map { it.toQuestionInfo() }
        nextQuestion()
        delay(1000) // just so it would look nice
        loadingState.value = QuizReady(state)
    }

    private fun calculateScore(): Int {
        var score = 0
        userAnswers.forEach { answer ->
            if (answer.correct) score++
        }
        return score
    }
}

package com.ziemowit.ts.trivia.app.screens.quiz

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_summary.QuizSummaryRoute
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.model.GivenAnswer
import com.ziemowit.ts.trivia.data.model.PotentialAnswer
import com.ziemowit.ts.trivia.data.model.QuestionInfo
import com.ziemowit.ts.trivia.data.model.emptyQuestionInfo
import com.ziemowit.ts.trivia.data.model.toQuestionInfo
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
    private var correctAnswers = 0

    // State objects
    private val difficulty = mutableStateOf(context.getString(quizArgs.difficulty.displayName))
    private val question = mutableStateOf(emptyQuestionInfo)
    private val isAnswerEnabled = mutableStateOf(true)
    private val questionCount: MutableState<String> = mutableStateOf("")


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

    init {
        Timber.d("QuizViewModel init diff: ${difficulty.value} currentQuestionIndex: ${currentQuestionIndex.value}")
        loadQuestions()
    }

    private fun onConfirmBackFromInterface() {
        Timber.d("onConfirmBackFromInterface")
        onConfirmBack()
        onBack()
    }

    private fun onQuizFinished() {
        Timber.d("onQuizFinished")
        // TODO - store the answers in the database on the go ?
        navigateToRoute(
            QuizSummaryRoute.getRoute(
                difficulty = quizArgs.difficulty,
                correctQuestions = correctAnswers,
                totalQuestions = questions.size
            )
        )
    }

    private fun onAnswerSelected(questionIndex: Int, potentialAnswer: PotentialAnswer) {
        viewModelScope.launch {
            isAnswerEnabled.value = false
            userAnswers.add(GivenAnswer(questionIndex, potentialAnswer.answerText, potentialAnswer.correct))
            if (potentialAnswer.correct) correctAnswers++
            Timber.d("onAnswerSelected, questionIndex: $questionIndex, potentialAnswer: $potentialAnswer correctAnswers: $correctAnswers")

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

            delay(500L)
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
        val dbQuestions = questionRepository.getQuestions(quizArgs.difficulty)
        Timber.w("Loaded questions: $dbQuestions")
        Timber.d("loadQuestions state: $state")
        //TODO - choose a subset of questions from the full list
        questions = dbQuestions.map { it.toQuestionInfo() }
        nextQuestion()
        delay(500L) // just so it would look nice
        loadingState.value = QuizReady(state)
    }
}

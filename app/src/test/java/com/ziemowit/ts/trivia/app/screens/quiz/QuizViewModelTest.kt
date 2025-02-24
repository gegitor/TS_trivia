package com.ziemowit.ts.trivia.app.screens.quiz

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.model.Category
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.data.model.PotentialAnswer
import com.ziemowit.ts.trivia.data.model.QuestionEntry
import com.ziemowit.ts.trivia.dispatchers.TestDispatcherProvider
import com.ziemowit.ts.trivia.nav.RouteNavigator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuizViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val context = mockk<Context>()
    private val savedStateHandle = mockk<SavedStateHandle>()
    private val routeNavigator = mockk<RouteNavigator>()
    private val questionRepository = mockk<QuestionRepository>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testDispatcherProvider = TestDispatcherProvider(testDispatcher)

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { context.getString(any()) } returns "Easy"
        every { context.getString(any(), *anyVararg()) } answers {
            "Question 1 of 2"
        }
        every { savedStateHandle.get<Int>("difficulty") } returns Difficulty.EASY.ordinal
        coEvery { questionRepository.getQuestions(any()) } returns listOf(
            QuestionEntry(1, Category.GAME_MECHANICS, Difficulty.EASY, "Question 1", "Answer 1", listOf("Wrong 1", "Wrong 2")),
            QuestionEntry(2, Category.GAME_MECHANICS, Difficulty.EASY, "Question 2", "Answer 2", listOf("Wrong 3", "Wrong 4"))
        )
        every { routeNavigator.navigateToRoute(any()) } returns Unit
        viewModel = QuizViewModel(context, savedStateHandle, routeNavigator, questionRepository, testDispatcherProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadQuestions should load questions and update state`() = runTest {
        viewModel.loadQuestions()
        advanceUntilIdle()
        coVerify { questionRepository.getQuestions(Difficulty.EASY) }
        assert(viewModel.loadingState.value is QuizReady)
        assert(viewModel.state.question.value.questionText == "Question 1")
    }

    @Test
    fun `onAnswerSelected with correct answer should update userAnswers and correctAnswers`() = runTest {
        val correctAnswer = PotentialAnswer("answer", true, false)
        viewModel.onAnswerSelected(0, correctAnswer)
        advanceUntilIdle()

        assert(viewModel.correctAnswers == 1)
        assert(viewModel.userAnswers.size == 1)
    }

    @Test
    fun `onAnswerSelected with wrong answer should update userAnswers and correctAnswers`() = runTest {
        val wrongAnswer = PotentialAnswer("answer", false, false)
        viewModel.onAnswerSelected(0, wrongAnswer)
        advanceUntilIdle()

        assert(viewModel.correctAnswers == 0)
        assert(viewModel.userAnswers.size == 1)
    }

    @Test
    fun `onQuizFinished should navigate to QuizSummaryRoute`() = runTest {
        viewModel.onQuizFinished()
        verify { routeNavigator.navigateToRoute(any()) }
    }
}

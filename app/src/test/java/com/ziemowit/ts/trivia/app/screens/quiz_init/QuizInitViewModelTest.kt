package com.ziemowit.ts.trivia.app.screens.quiz_init

import android.content.SharedPreferences
import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuizInitViewModelTest {

    @MockK
    lateinit var mockRouteNavigator: RouteNavigator

    @MockK
    lateinit var mockSharedPreferences: SharedPreferences

    @MockK
    lateinit var mockSharedPreferencesEditor: SharedPreferences.Editor

    private lateinit var viewModel: QuizInitViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        every { mockSharedPreferences.getBoolean(any(), any()) } returns false
        every { mockSharedPreferences.edit() } returns mockSharedPreferencesEditor
        every { mockSharedPreferencesEditor.putBoolean(any(), any()) } returns mockSharedPreferencesEditor
        every { mockSharedPreferencesEditor.apply() } returns Unit
        every { mockRouteNavigator.navigateToRoute(any()) } returns Unit

        viewModel = QuizInitViewModel(mockRouteNavigator, mockSharedPreferences)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test onNavigateToQuiz`() {
        val difficulty = Difficulty.EASY

        viewModel.interactions.onNavigateToQuiz(difficulty)

        testDispatcher.scheduler.advanceUntilIdle()

        verify { mockRouteNavigator.navigateToRoute("quiz?difficulty=0") }
    }

    @Test
    fun `test setHiddenDifficultyVisibility`() {
        val visible = true

        viewModel.interactions.setHiddenDifficultyVisibility(visible)

        testDispatcher.scheduler.advanceUntilIdle()

        verify { mockSharedPreferencesEditor.putBoolean(viewModel.PREF_HIDDEN_DIFFICULTY, visible) }
        verify { mockSharedPreferencesEditor.apply() }
        assert(viewModel.state.isSecretDifficultyVisible.value == visible)
    }
}

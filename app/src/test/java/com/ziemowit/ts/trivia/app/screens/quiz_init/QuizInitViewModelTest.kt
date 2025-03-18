package com.ziemowit.ts.trivia.app.screens.quiz_init

import android.content.SharedPreferences
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz_init.QuizInitViewModel
import com.ziemowit.ts.trivia.audio.Sound
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
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
    lateinit var mockSoundRepository: SoundRepository

    @MockK
    lateinit var mockPreferencesRepository: PreferencesRepository

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
        every { mockSoundRepository.play(any()) } returns Unit
        every { mockPreferencesRepository.getIsSecretDifficultyVisible() } returns false
        every { mockPreferencesRepository.setIsSecretDifficultyVisible(any()) } returns Unit

        viewModel = QuizInitViewModel(mockRouteNavigator, mockPreferencesRepository, mockSoundRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test onNavigateToQuiz`() {
        val difficulty = Difficulty.EASY
        viewModel.interactions.onNavigateToQuiz(difficulty)

        verify { mockRouteNavigator.navigateToRoute("quiz?difficulty=0") }
    }

    @Test
    fun `test setHiddenDifficultyVisibility`() {
        val visible = true

        viewModel.interactions.setHiddenDifficultyVisibility(visible)

        verify { mockPreferencesRepository.setIsSecretDifficultyVisible(visible) }
        assert(viewModel.state.isSecretDifficultyVisible.value == visible)
    }

    @Test
    fun `test setHiddenDifficultyVisibility plays sound`() {
        val visible = true

        viewModel.interactions.setHiddenDifficultyVisibility(visible)

        verify { mockSoundRepository.play(Sound.HiWojtek) }
    }
}

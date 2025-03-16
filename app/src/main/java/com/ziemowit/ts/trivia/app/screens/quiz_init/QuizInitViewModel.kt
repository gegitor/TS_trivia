package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz.QuizRoute
import com.ziemowit.ts.trivia.audio.Sound
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizInitViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    private val preferencesRepository: PreferencesRepository,
    private val soundRepository: SoundRepository
) :
    ParentViewModel(routeNavigator) {

    private val email = mutableStateOf("qinit@email")
    private val isSecretDifficultyVisible = mutableStateOf(false)

    init {
        isSecretDifficultyVisible.value = preferencesRepository.getIsSecretDifficultyVisible()
        Timber.d("QuizInitViewModel init, isSecretDifficultyVisible: ${isSecretDifficultyVisible.value}")
    }

    internal val interactions = QuizInitScreenInteractions(
        onBackClicked = ::onBack,
        onNavigateToQuiz = ::onNavigateToQuiz,
        setHiddenDifficultyVisibility = ::setHiddenDifficultyVisibility,
    )

    internal val state = QuizInitState(
        email = email,
        isSecretDifficultyVisible = isSecretDifficultyVisible,
    )

    private fun onNavigateToQuiz(difficulty: Difficulty) {
        Timber.d("onNavigateToQuiz, route: ${QuizRoute.getRoute(difficulty)}")
        navigateToRouteWithPop(QuizRoute.getRoute(difficulty), QuizInitRoute.route)
    }

    private fun setHiddenDifficultyVisibility(visible: Boolean) {
        if (visible) {
            soundRepository.play(Sound.HiWojtek)
        }
        Timber.d("setHiddenDifficultyVisibility: $visible")
        isSecretDifficultyVisible.value = visible
        preferencesRepository.setIsSecretDifficultyVisible(visible)
    }
}

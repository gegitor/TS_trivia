package com.ziemowit.ts.trivia.app.screens.quiz_init

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz.QuizRoute
import com.ziemowit.ts.trivia.audio.Sound
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.trivia.data.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class QuizInitViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    private val sharedPreferences: SharedPreferences,
    private val soundRepository: SoundRepository
) :
    ParentViewModel(routeNavigator) {

    val PREF_HIDDEN_DIFFICULTY = "PREF_HIDDEN_DIFFICULTY"
    private val email = mutableStateOf("qinit@email")
    private val isSecretDifficultyVisible = mutableStateOf(false)


    init {
        isSecretDifficultyVisible.value = sharedPreferences.getBoolean(PREF_HIDDEN_DIFFICULTY, false)
        Timber.d("ZZZ QuizInitViewModel init, isSecretDifficultyVisible: ${isSecretDifficultyVisible.value}")
    }

    internal val interactions = QuizInitScreenInteractions(
        onBackClicked = ::onBackClicked,
        onNavigateToQuiz = ::onNavigateToQuiz,
        setHiddenDifficultyVisibility = ::setHiddenDifficultyVisibility,
    )

    internal val state = QuizInitState(
        email = email,
        isSecretDifficultyVisible = isSecretDifficultyVisible,
    )

    private fun onNavigateToQuiz(difficulty: Difficulty) {
        Timber.d("ZZZ onNavigateToQuiz, route: ${QuizRoute.getRoute(difficulty)}")
        navigateToRoute(QuizRoute.getRoute(difficulty))
    }

    private fun setHiddenDifficultyVisibility(visible: Boolean) {
        if (visible) {
            soundRepository.play(Sound.HiWojtek)
        }
        Timber.d("ZZZ setHiddenDifficultyVisibility: $visible")
        isSecretDifficultyVisible.value = visible
        sharedPreferences.edit().putBoolean(PREF_HIDDEN_DIFFICULTY, visible).apply()
    }
}



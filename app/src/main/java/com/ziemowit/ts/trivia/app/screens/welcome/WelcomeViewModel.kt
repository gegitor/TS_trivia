package com.ziemowit.ts.trivia.app.screens.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.home.HomeRoute
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by Ziemowit Pazderski on 3/2/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    routeNavigator: RouteNavigator,
) : ParentViewModel(routeNavigator) {

    private val _welcomeLoadingState: MutableState<WelcomeLoadingState> = mutableStateOf(WelcomeLoadingState.Loading)
    val state: State<WelcomeLoadingState> = _welcomeLoadingState

    internal val interactions = WelcomeScreenInteractions(
        onNameEntered = ::onNameEntered,
        onDismissErrorDialog = ::onDismissErrorDialog,
    )
    private val name = preferencesRepository.getUserName()

    init {
        if (name.isNullOrBlank()) {
            _welcomeLoadingState.value = WelcomeLoadingState.AskName(false)
        } else {
            _welcomeLoadingState.value = WelcomeLoadingState.Ready(name)
            navigateToRouteWithPop(HomeRoute.getRoute(name), WelcomeRoute.route) //TODO test delay // should be screen
        }
    }

    fun onNameEntered(name: String) {
        if (isNameValid(name)) {
            preferencesRepository.setUserName(name)
            _welcomeLoadingState.value = WelcomeLoadingState.Ready(name) // on ready a launched effect to navigate home?
            navigateToRouteWithPop(HomeRoute.getRoute(name), WelcomeRoute.route) //TODO test delay // should be screen
        } else {
            _welcomeLoadingState.value = WelcomeLoadingState.AskName(true)
        }
    }

    fun onDismissErrorDialog() {
        _welcomeLoadingState.value = WelcomeLoadingState.AskName(false)
    }

    private fun isNameValid(name: String) : Boolean {
        if (name.isBlank()) return false
        if (name.length < 3) return false
        if (name.length > 12) return false

        return true
    }
}

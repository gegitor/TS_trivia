package com.ziemowit.ts.trivia.app.screens.main

import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    private val preferencesRepository: PreferencesRepository,
) : ParentViewModel(routeNavigator) {

    fun checkInitialDestination() {
        Timber.d("checkInitialDestination")
        val name = preferencesRepository.getUserName()
        if (name.isNullOrBlank()) {
            onNameAbsent()
        } else {
            onNamePresent(name)
        }
    }

    private fun onNamePresent(userName: String) {
        Timber.d("onNamePresent: $userName")
        viewModelScope.launch {
            navigateToHome(userName)
        }
    }

    private fun onNameAbsent() {
        Timber.d("onNameAbsent")
        viewModelScope.launch {
            navigateToWelcome()
        }
    }
}

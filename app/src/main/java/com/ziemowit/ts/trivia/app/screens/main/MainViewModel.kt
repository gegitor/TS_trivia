package com.ziemowit.ts.trivia.app.screens.main

import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(routeNavigator: RouteNavigator, private val preferencesRepository: PreferencesRepository) :
    ParentViewModel(routeNavigator) {

    private val _navigationCommands = MutableSharedFlow<NavigationCommand>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigationCommands = _navigationCommands.asSharedFlow()

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
            _navigationCommands.emit(NavigateToHome(userName))
        }
    }

    private fun onNameAbsent() {
        Timber.d("onNameAbsent")
        viewModelScope.launch {
            _navigationCommands.emit(NavigateToWelcome)
        }
    }
}

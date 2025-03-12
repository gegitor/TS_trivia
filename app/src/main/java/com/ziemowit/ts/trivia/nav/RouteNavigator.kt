package com.ziemowit.ts.trivia.nav

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String)
    suspend fun navigateToWelcome()
    suspend fun navigateToHome(userName: String)

    val navigationState: StateFlow<NavigationState>
    val navigationCommands: Flow<NavigationCommand>
}

class RouteNavigatorImpl : RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override val navigationCommands = MutableSharedFlow<NavigationCommand>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )


    override suspend fun navigateToWelcome() {
        navigationCommands.emit(NavigateToWelcome)
    }

    override suspend fun navigateToHome(userName: String) {
        navigationCommands.emit(NavigateToHome(userName))
    }

    override fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() {
        Timber.w("navigateUp")
        return navigate(NavigationState.NavigateUp())
    }

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}

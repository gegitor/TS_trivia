package com.ziemowit.ts.trivia.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String, inclusive: Boolean = false)
    fun navigateToRoute(route: String)
    fun navigateToRouteWithPop(route: String, popRoute: String)

    val navigationState: StateFlow<NavigationState>
}

class RouteNavigatorImpl : RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String, inclusive: Boolean) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() {
        Timber.w("navigateUp")
        return navigate(NavigationState.NavigateUp())
    }

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    override fun navigateToRouteWithPop(route: String, popRoute: String) = navigate(NavigationState.NavigateToRouteWithPop(route, popRoute))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        Timber.d("RouteNavigatorImpl navigate: $state")
        navigationState.value = state
    }
}

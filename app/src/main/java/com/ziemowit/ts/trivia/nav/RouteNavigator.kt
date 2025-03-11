package com.ziemowit.ts.trivia.nav

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ziemowit.ts.trivia.app.screens.main.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String)
    fun navigateToScreen(navController: NavHostController, screen: Screen)
    fun navigateToScreen(navController: NavHostController, route: String)

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

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() {
        Timber.w("navigateUp")
        return navigate(NavigationState.NavigateUp())
    }

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    override fun navigateToScreen(navController: NavHostController, screen: Screen) {
        navController.navigate(
            route = screen.route,
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(screen.route, inclusive = false, saveState = true)
                .setRestoreState(true)
                .build()
        )
    }

    override fun navigateToScreen(navController: NavHostController, route: String) {
        navController.navigate(
            route = route,
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(route, inclusive = false, saveState = true)
                .setRestoreState(true)
                .build()
        )
    }

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}

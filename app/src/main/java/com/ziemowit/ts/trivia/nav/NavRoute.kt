package com.ziemowit.ts.trivia.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import timber.log.Timber

/**
 * A route the app can navigate to.
 */
interface NavRoute<T : RouteNavigator> {

    val route: String

    /**
     * Returns the screen's content.
     */
    @Composable
    fun Content(viewModel: T)

    /**
     * Returns the screen's ViewModel. Needs to be overridden so that Hilt can generate code for the factory for the ViewModel class.
     */
    @Composable
    fun viewModel(): T

    /**
     * Override when this page uses arguments.
     *
     * We do it here and not in the [NavigationComponent to keep it centralized]
     */
    fun getArguments(): List<NamedNavArgument> = listOf()

    /**
     * Generates the composable for this route.
     */
    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        Timber.d("composable route: $route")
        builder.composable(route, getArguments()) {
            val viewModel = viewModel()
            val viewStateAsState by viewModel.navigationState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                updateNavigationState(navHostController, viewStateAsState, viewModel::onNavigated)
            }

            Content(viewModel)
        }
    }

//    /**
//     * Generates the composable for this route.
//     */
//    fun navigation(
//        builder: NavGraphBuilder,
//        navHostController: NavHostController
//    ) {
//        builder.navigation(route, getArguments()) {
//            val viewModel = viewModel()
//            val viewStateAsState by viewModel.navigationState.collectAsState()
//
//            LaunchedEffect(viewStateAsState) {
//                Timber.d("Nav", "ZZZ ${this@NavRoute} updateNavigationState to $viewStateAsState")
//                updateNavigationState(navHostController, viewStateAsState, viewModel::onNavigated)
//            }
//
//            Content(viewModel)
//        }
//    }

    /**
     * Navigates to [navigationState].
     */
    private fun updateNavigationState(
        navHostController: NavHostController,
        navigationState: NavigationState,
        onNavigated: (navState: NavigationState) -> Unit,
    ) {
        Timber.d("updateNavigationState navigationState: $navigationState")

        when (navigationState) {
            is NavigationState.NavigateToRoute -> {
                navHostController.navigate(navigationState.route)
            }

            is NavigationState.NavigateToRouteWithPop -> {
                navHostController.navigate(navigationState.route) { popUpTo(navigationState.popRoute) { inclusive = true } }
            }

            is NavigationState.PopToRoute -> {
                navHostController.popBackStack(navigationState.staticRoute, false)
            }

            is NavigationState.NavigateUp -> {
                navHostController.navigateUp()
            }

            is NavigationState.Idle -> {
            }
        }
        onNavigated(navigationState)
    }
}

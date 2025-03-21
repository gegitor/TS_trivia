package com.ziemowit.ts.trivia.app.screens.quizzing.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.app.screens.quizzing.main.Screen
import com.ziemowit.ts.trivia.nav.NavRoute

//private const val homeRoute: String = "home"
////
////
//fun NavGraphBuilder.quizScreenHierarchy1() {
//    navigation(
////        navController = navHostController,
//        route = homeRoute,
//        startDestination = homeRoute,
////        modifier = Modifier.padding(paddingValues)
//    ) {
////        homeScreen()
//    }
//}
//
//@Composable
//fun NavGraphBuilder.homeScreenHierarchy2(
//    navHostController: NavHostController,
//    paddingValues: PaddingValues
//) {
//    NavHost(
//        navController = navHostController,
//        startDestination = homeRoute,
//        modifier = Modifier.padding(paddingValues)
//    ) {
//        homeScreen()
//    }
//}
//
//fun NavGraphBuilder.homeScreen() {
//    composable(
//        route = homeRoute
//    ) {
//        val viewModel: QuizInitViewModel = hiltViewModel()
//        QuizInitScreen(
//            viewModel = viewModel,
//        )
//    }
//}


internal class HomeArgs(val userName: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(savedStateHandle[userNameArg] ?: "")
}

private const val userNameArg = "userNameArg"


object HomeRoute : NavRoute<HomeViewModel> {

    override val route = "${Screen.Home.route}?$userNameArg={$userNameArg}"

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) =
        HomeScreen(Modifier, viewModel.state, viewModel.interactions)

    fun getRoute(userName: String) = route.replace("{$userNameArg}", userName)
}
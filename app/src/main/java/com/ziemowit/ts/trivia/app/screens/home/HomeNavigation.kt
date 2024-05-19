package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.nav.NavRoute

//import androidx.navigation.navigation

//private const val homeRoute: String = "home"
//
//
//fun NavGraphBuilder.quizScreenHierarchy1() {
//    navigation(
////        navController = navHostController,
//        route = homeRoute,
//        startDestination = homeRoute,
////        modifier = Modifier.padding(paddingValues)
//    ) {
//        homeScreen()
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





object QuizInitRoute : NavRoute<HomeViewModel> {

    override val route = "quizInit/"

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) =
        QuizInitScreen(Modifier, viewModel.state, viewModel.interactions)
}
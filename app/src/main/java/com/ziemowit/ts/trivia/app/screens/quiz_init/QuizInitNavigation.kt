package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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





object QuizInitRoute : NavRoute<QuizInitViewModel> {

    override val route = "quizInit/"

    @Composable
    override fun viewModel(): QuizInitViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: QuizInitViewModel) =
        QuizInitScreen(Modifier, viewModel.state, viewModel.interactions)
}
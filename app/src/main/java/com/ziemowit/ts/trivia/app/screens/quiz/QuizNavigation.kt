package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.nav.NavRoute

private const val difficultyArg = "difficulty"

//fun NavController.navigateToQuizScreen(difficulty: String) {
//    this.navigate("${Screen.Quiz.route}/$difficulty")
//}
//
//
//fun NavGraphBuilder.quizScreen() {
//    composable(
//        route = "quiz/{$difficultyArg}",
//        arguments = listOf(navArgument(difficultyArg) {
//            type = NavType.StringType
//        })
//    ) {
//        val viewModel: QuizViewModel = hiltViewModel()
//        QuizScreen(
//            viewModel = viewModel,
////            onNavigateBack = onNavigateBack
//        )
//    }
//}


internal class QuizArgs(val difficulty: Difficulty) {
    constructor(savedStateHandle: SavedStateHandle) :
    this(Difficulty.fromOrdinal(checkNotNull(savedStateHandle[difficultyArg])))
}


object QuizRoute : NavRoute<QuizViewModel> {

    override val route = "quiz?$difficultyArg={$difficultyArg}"

    @Composable
    override fun viewModel(): QuizViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: QuizViewModel) =
        QuizScreen(Modifier, viewModel.loadingState.value, viewModel.interactions)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(difficultyArg) { type = NavType.IntType })

    fun getRoute(difficulty: Difficulty) = route.replace("{$difficultyArg}", "${difficulty.ordinal}")
}
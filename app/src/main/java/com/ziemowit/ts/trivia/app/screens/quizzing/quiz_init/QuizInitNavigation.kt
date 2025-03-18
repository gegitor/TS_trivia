package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_init

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.nav.NavRoute

object QuizInitRoute : NavRoute<QuizInitViewModel> {

    override val route = "quizInit"

    @Composable
    override fun viewModel(): QuizInitViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: QuizInitViewModel) =
        QuizInitScreen(Modifier, viewModel.state, viewModel.interactions)
}
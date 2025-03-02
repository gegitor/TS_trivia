package com.ziemowit.ts.trivia.app.screens.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.nav.NavRoute

object WelcomeRoute : NavRoute<WelcomeViewModel> {

    override val route = "welcome"

    @Composable
    override fun viewModel(): WelcomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: WelcomeViewModel) =
        WelcomeScreen(Modifier, viewModel.state.value, viewModel.interactions)
}
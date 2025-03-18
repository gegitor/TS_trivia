package com.ziemowit.ts.trivia.app.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.nav.NavRoute


object ProfileRoute : NavRoute<ProfileViewModel> {

    override val route = "profile"

    @Composable
    override fun viewModel(): ProfileViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ProfileViewModel) =
        ProfileScreen(Modifier, viewModel.uiState.value, viewModel.interactions)
}
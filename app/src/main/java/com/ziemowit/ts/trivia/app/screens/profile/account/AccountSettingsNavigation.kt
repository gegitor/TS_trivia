package com.ziemowit.ts.trivia.app.screens.profile.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.nav.NavRoute


object AccountSettingsRoute : NavRoute<AccountSettingsViewModel> {

    override val route = "account_settings"

    @Composable
    override fun viewModel(): AccountSettingsViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AccountSettingsViewModel) =
        AccountSettingsScreen(Modifier, viewModel)
}
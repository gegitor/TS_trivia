package com.ziemowit.ts.trivia.app.screens.main

sealed interface NavigationCommand

internal data object NavigateToWelcome : NavigationCommand
internal class NavigateToHome(val name: String) : NavigationCommand
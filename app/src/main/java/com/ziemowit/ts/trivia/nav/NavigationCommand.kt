package com.ziemowit.ts.trivia.nav


/**
 * Created by Ziemowit Pazderski on 3/11/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
sealed interface NavigationCommand

internal data object NavigateToWelcome : NavigationCommand
internal class NavigateToHome(val name: String) : NavigationCommand
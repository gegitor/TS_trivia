package com.ziemowit.ts.trivia.app.screens.home

internal data class HomeScreenInteractions(
    val onBackClicked: () -> Unit,
//    val onNavigateToQuizInit: () -> Unit,
    val onContinueQuizClick: () -> Unit,
    val onQuickPlayClick: () -> Unit,
    val onCreateCustomQuizClick: () -> Unit,
    val onChallengeAcceptClick: (String) -> Unit,
    val onShareClick: (String) -> Unit,
    val dismissError: () -> Unit,
) {

    companion object {
        val STUB = HomeScreenInteractions(
            onBackClicked = {},
//            onNavigateToQuizInit = {},
            onContinueQuizClick = {},
            onQuickPlayClick = {},
            onCreateCustomQuizClick = {},
            onChallengeAcceptClick = {},
            onShareClick = {},
            dismissError = {},
        )
    }
}

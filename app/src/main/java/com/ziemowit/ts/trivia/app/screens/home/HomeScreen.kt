package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import timber.log.Timber

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState.stub(),
    interactions: HomeScreenInteractions = HomeScreenInteractions.STUB,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.BottomCenter,
    ) {
        TextButton(
            onClick = { interactions.onNavigateToQuizInit() }
        ) {
            Text(text = "home screen: ${state.email}", fontSize = 20.sp) //TODO - change to some greeting
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

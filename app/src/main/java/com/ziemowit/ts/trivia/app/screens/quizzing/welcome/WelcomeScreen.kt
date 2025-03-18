package com.ziemowit.ts.trivia.app.screens.quizzing.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
internal fun WelcomeScreen(
    modifier: Modifier = Modifier,
    state: WelcomeLoadingState,
    interactions: WelcomeScreenInteractions,
) {
    when (state) {
        is WelcomeLoadingState.Loading -> LoadingScreen()
        is WelcomeLoadingState.AskName -> AskNameScreen(modifier, state, interactions)
        is WelcomeLoadingState.Ready -> ReadyScreen(modifier, state, interactions)
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun AskNameScreen(modifier: Modifier, state: WelcomeLoadingState.AskName, interactions: WelcomeScreenInteractions) {
    var name by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the Quiz!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Please enter your name:",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        NameInputField(name = name, onNameChange = { name = it })
        Spacer(modifier = Modifier.height(16.dp))
        SubmitButton(name = name.text, onSubmit = interactions.onNameEntered)
    }

    if (state.invalidNameNotify) {
        ErrorDialog(interactions.onDismissErrorDialog)
    }
}

@Composable
private fun NameInputField(
    name: TextFieldValue,
    onNameChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Your Name") },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun SubmitButton(
    name: String,
    onSubmit: (String) -> Unit
) {
    Button(
        onClick = { onSubmit(name) },
        enabled = name.isNotBlank() && name.length in 2..12,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Start Quiz", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun ReadyScreen(
    modifier: Modifier,
    state: WelcomeLoadingState.Ready,
    interactions: WelcomeScreenInteractions
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome, ${state.userName}!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "You're all set to begin. Enjoy the quiz!",
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
private fun ErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Invalid Name", style = MaterialTheme.typography.titleMedium) },
        text = { Text(text = "Please enter a name between 3 and 12 characters.") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK", color = MaterialTheme.colorScheme.primary)
            }
        }
    )
}


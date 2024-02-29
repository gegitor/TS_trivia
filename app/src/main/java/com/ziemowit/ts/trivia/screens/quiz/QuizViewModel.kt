package com.ziemowit.ts.trivia.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val diffArgs = QuizArgs(savedStateHandle)

    fun kk() {
        diffArgs.difficulty
    }
    val difficulty: State<String> = mutableStateOf(diff)

}
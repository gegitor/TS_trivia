package com.ziemowit.ts.trivia.app.screens.main

import androidx.lifecycle.ViewModel
import com.ziemowit.ts.trivia.nav.RouteNavigator
import timber.log.Timber

abstract class ParentViewModel(private val routeNavigator: RouteNavigator/*savedStateHandle: SavedStateHandle*/) :
    ViewModel(), RouteNavigator by routeNavigator {


    open fun onBackClicked() {
        Timber.d("ZZZ onBackClicked")
        navigateUp()
    }
}
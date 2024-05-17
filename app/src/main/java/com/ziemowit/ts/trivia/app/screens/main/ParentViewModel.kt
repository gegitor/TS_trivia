package com.ziemowit.ts.trivia.app.screens.main

import androidx.lifecycle.ViewModel
import com.ziemowit.ts.trivia.nav.RouteNavigator

abstract class ParentViewModel(private val routeNavigator: RouteNavigator/*savedStateHandle: SavedStateHandle*/) :
    ViewModel(), RouteNavigator by routeNavigator
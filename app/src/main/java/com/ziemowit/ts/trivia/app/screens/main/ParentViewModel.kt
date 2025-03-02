package com.ziemowit.ts.trivia.app.screens.main

import androidx.lifecycle.ViewModel
import com.ziemowit.ts.trivia.nav.RouteNavigator
import timber.log.Timber

abstract class ParentViewModel(private val routeNavigator: RouteNavigator) : ViewModel(),
    RouteNavigator by routeNavigator {

    override fun navigateUp() {
        Timber.w("ParentViewModel navigateUp")
        routeNavigator.navigateUp()

    }

    open fun onBack() {
        Timber.w("ParentViewModel onBack")
        navigateUp()
    }

//    open suspend fun initialDataLoad() {}
}
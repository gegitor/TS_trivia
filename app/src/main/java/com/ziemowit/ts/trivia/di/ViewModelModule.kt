package com.ziemowit.ts.trivia.di

import com.ziemowit.ts.trivia.nav.RouteNavigator
import com.ziemowit.ts.trivia.nav.RouteNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = RouteNavigatorImpl()
}
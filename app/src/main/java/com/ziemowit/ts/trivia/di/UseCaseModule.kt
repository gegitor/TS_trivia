package com.ziemowit.ts.trivia.di

import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.usecases.GetQuestionsUseCase
import com.ziemowit.ts.trivia.data.usecases.GetQuestionsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun bindGetQuestionsUseCase(questionRepository: QuestionRepository): GetQuestionsUseCase = GetQuestionsUseCaseImpl(questionRepository)
}
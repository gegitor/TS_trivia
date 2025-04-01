package com.ziemowit.ts.trivia.di

import com.ziemowit.ts.trivia.domain.repository.QuestionRepository
import com.ziemowit.ts.trivia.domain.usecase.GetQuestionsUseCase
import com.ziemowit.ts.trivia.domain.usecase.GetQuestionsUseCaseImpl
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
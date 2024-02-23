package com.ziemowit.ts.trivia.di

//import android.app.Application
//import android.content.Context
//import com.ziemowit.ts.trivia.app.TriviaApp
//import me.tatarka.inject.annotations.Component
//import me.tatarka.inject.annotations.Provides
//import me.tatarka.inject.annotations.Scope

//@Scope
//@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
//annotation class MyScope
//
//@Scope
//annotation class ApplicationScope
//
//@Component
//abstract class AppComponent(@get:Provides protected val app: TriviaApp) {
//
////    abstract val context: Context
//
//    @Provides
//    protected fun provideContext(): Context = app.applicationContext
//
//    interface ApplicationComponentProvider {
//        val component: AppComponent
//    }
//
//    val Context.applicationComponent get() = (applicationContext as ApplicationComponentProvider).component
//
////    companion object {
////        private var instance: AppComponent? = null
////
////        /**
////         * Get a singleton instance of [AppComponent].
////         */
////        fun getInstance(context: Context) = instance ?: AppComponent::class.create(
////            context.applicationContext as Application
////        ).also { instance = it }
////    }
//}
package com.ziemowit.ts.trivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.ziemowit.ts.trivia.app.screens.quizzing.main.MainScreen
import com.ziemowit.ts.trivia.app.screens.quizzing.splash.SplashViewModel
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.ui_common.theme.TSTriviaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val splashViewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var soundRepository: SoundRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
//            val navController = rememberNavController()
//            StyleSystemBars()

            TSTriviaTheme {
                MainScreen()
            }
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        soundRepository.stopAll()
//    }
}

//@Composable
//fun StyleSystemBars() {
//    val systemUiController = rememberSystemUiController()
//    DisposableEffect(systemUiController) {
//        systemUiController.setStatusBarColor(
//            color = Color.Transparent,
//            darkIcons = true,
//        )
//        systemUiController.setNavigationBarColor(
//            color = Color.Black,
//            darkIcons = false,
//        )
//
//        onDispose {}
//    }
//}
package com.ziemowit.ts.trivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.ziemowit.ts.trivia.app.screens.splash.SplashViewModel
import com.ziemowit.ts.trivia.audio.Sound
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.trivia.screens.main.MainScreen
import com.ziemowit.ts.trivia.ui.theme.TSTriviaTheme
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
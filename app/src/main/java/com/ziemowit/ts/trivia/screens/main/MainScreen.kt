package com.ziemowit.ts.trivia.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ziemowit.ts.trivia.screens.home.HomeScreen
import com.ziemowit.ts.trivia.screens.quiz.QuizScreen
import com.ziemowit.ts.trivia.screens.quiz.quizScreen
import timber.log.Timber


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomTriviaBar(navController) },
    )
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            quizScreen()
//            composable(
//                route = Screen.Quiz.route,
//                arguments = listOf(navArgument("difficulty") {
//                    type = NavType.StringType
//                    defaultValue = "todo"
//                }),
//            ) { backStackEntry ->
//                QuizScreen(backStackEntry.arguments?.getString("difficulty") ?: "todo")
//            }
        }
    }
}

@Composable
fun BottomTriviaBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color.Black,
        contentColor = Color.Red,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationItem(navController, Screen.Home, Icons.Default.Home, "Home")
            NavigationItem(navController, Screen.Search, Icons.Default.Search, "Search")
            NavigationItem(navController, Screen.Profile, Icons.Default.AccountCircle, "Profile")
        }
    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
    data object Quiz : Screen("quiz?difficulty={difficulty}")
}


@Composable
fun SearchScreen() {
    Text("SearchScreen")
}

@Composable
fun ProfileScreen() {
    Text("ProfileScreen")
}


@Composable
fun NavigationItem(
    navController: NavHostController, //TODO - move out of composable?
    screen: Screen,
    icon: ImageVector,
    label: String
) {
    IconButton(
        onClick = {
            Timber.d("ZZZ navigate: ${screen.route}")
            navController.navigate(
                route = screen.route,
                navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setPopUpTo(Screen.Home.route, false) //will ruin current backstack
                    .build()
            )
        }
    ) {
        Icon(imageVector = icon, contentDescription = label)
    }
}
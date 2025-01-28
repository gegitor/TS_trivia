package com.ziemowit.ts.trivia.app.screens.main

//import com.ziemowit.ts.trivia.app.screens.quiz_init.quizScreenHierarchy1
import androidx.compose.animation.AnimatedContentScope
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ziemowit.ts.trivia.app.screens.home.HomeRoute
import com.ziemowit.ts.trivia.app.screens.quiz.QuizRoute
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitRoute


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
            homeScreen(this@NavHost, navController)
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
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
            NavigationItem(onClick = { onHomeClick(navController) }, icon = Icons.Default.Home, label = "Home")
            NavigationItem(onClick = { onSearchClick(navController) }, icon = Icons.Default.Search, label = "Search")
            NavigationItem(onClick = { onProfileClick(navController) }, icon = Icons.Default.AccountCircle, label = "Profile")
        }
    }
}

private fun navigateToScreen(navController: NavHostController, screen: Screen) {
    navController.navigate(
        route = screen.route,
        navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(Screen.Home.route, inclusive = false, saveState = true)
            .setRestoreState(true)
            .build()
    )
}

fun onHomeClick(navController: NavHostController) {
    navigateToScreen(navController, Screen.Home)
}

fun onSearchClick(navController: NavHostController) {
    navigateToScreen(navController, Screen.Search)
}

fun onProfileClick(navController: NavHostController) {
    navigateToScreen(navController, Screen.Profile)
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
}


fun homeScreen(builder: NavGraphBuilder, navController: NavHostController) {
    HomeRoute.composable(builder, navController)
    QuizInitRoute.composable(builder, navController)
    QuizRoute.composable(builder, navController)
//            quizScreenHierarchy1()
//            composable(Screen.Home.route) {
//                HomeRoute.composable(this, navController)
//                HomeScreen()
//            }
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
    onClick: () -> Unit,
    icon: ImageVector,
    label: String
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = label)
    }
}

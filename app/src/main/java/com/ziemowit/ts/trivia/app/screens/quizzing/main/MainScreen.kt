package com.ziemowit.ts.trivia.app.screens.quizzing.main

//import com.ziemowit.ts.trivia.app.screens.quiz_init.quizScreenHierarchy1
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ziemowit.ts.trivia.app.screens.profile.account.AccountSettingsRoute
import com.ziemowit.ts.trivia.app.screens.profile.start.ProfileRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.home.HomeRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz.QuizRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz_init.QuizInitRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary.QuizSummaryRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.welcome.WelcomeRoute
import com.ziemowit.ts.trivia.nav.navigateToScreen
import timber.log.Timber

@SuppressLint("RestrictedApi")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { controller, destination, _ ->
        val routes = controller.currentBackStack.value
            .mapNotNull { it.destination.route }
            .joinToString(separator = ", ")
        Timber.d("BackStack: $routes destination: $destination")
    }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val isInAuthFlow = currentRoute == Screen.Welcome.route

    Scaffold(
        bottomBar = { if (!isInAuthFlow) BottomTriviaBar(navController) },
    )
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(if (!isInAuthFlow) paddingValues else PaddingValues(0.dp))
        ) {
            welcomeScreen(this, navController)
            homeScreen(this, navController)
            composable(Screen.Search.route) {
                SearchScreen()
            }
            profileScreen(this, navController)
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

fun onHomeClick(navController: NavHostController) {
    navController.navigateToScreen(Screen.Home)
}

fun onSearchClick(navController: NavHostController) {
    navController.navigateToScreen(Screen.Search)
}

fun onProfileClick(navController: NavHostController) {
    navController.navigateToScreen(Screen.Profile)
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
    data object Welcome : Screen("welcome")
}

fun welcomeScreen(builder: NavGraphBuilder, navController: NavHostController) {
    WelcomeRoute.composable(builder, navController)
}

fun homeScreen(builder: NavGraphBuilder, navController: NavHostController) {
    HomeRoute.composable(builder, navController)
    QuizInitRoute.composable(builder, navController)
    QuizRoute.composable(builder, navController)
    QuizSummaryRoute.composable(builder, navController)
}

@Composable
fun SearchScreen() {
    Text("SearchScreen")
}

fun profileScreen(builder: NavGraphBuilder, navController: NavHostController) {
    ProfileRoute.composable(builder, navController)
    AccountSettingsRoute.composable(builder, navController)
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

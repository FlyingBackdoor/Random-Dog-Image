package dev.sohair.timepass.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.sohair.timepass.data.local.CachedImageDao
import dev.sohair.timepass.ui.screens.GenerateImageScreen
import dev.sohair.timepass.ui.screens.HomeScreen
import dev.sohair.timepass.ui.screens.ImageHistoryScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Generate : Screen("generate")
    object History : Screen("history")
}

@Composable
fun NavigationHost(navController: NavHostController, dao: CachedImageDao) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Generate.route) { GenerateImageScreen(dao) }
        composable(Screen.History.route) { ImageHistoryScreen(dao) }
    }
}

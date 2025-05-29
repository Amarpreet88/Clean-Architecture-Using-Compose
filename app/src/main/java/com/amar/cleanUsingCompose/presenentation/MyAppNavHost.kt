package com.amar.cleanUsingCompose.presenentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("home") {
            TodoScreen(navController)
        }
        composable(
            route = "detail/{description}/{url}",
            arguments = listOf(
                navArgument("description") { type = NavType.StringType },
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val description = backStackEntry.arguments?.getString("description") ?: ""
            val url = backStackEntry.arguments?.getString("url") ?: "Unknown"

            // Pass these to your ViewModel or Composable directly
            TodoDetailScreen(description = description, url = url)

        }
    }
}
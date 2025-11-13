package uvg.giancarlo.afinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uvg.giancarlo.afinal.Screen.Pantalla1
import uvg.giancarlo.afinal.Screen.Pantalla2
import uvg.giancarlo.afinal.Screen.Pantalla3
import uvg.giancarlo.afinal.Screen.Pantalla4


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Pantalla1.route

    ) {

        composable(Screen.Pantalla1.route) {
            Pantalla1(
                changeScreen = { screen ->
                    navController.navigate(screen.route)
                }
            )
        }

        composable(Screen.Pantalla2.route) {
            Pantalla2(
                changeScreen = { screen ->
                    navController.navigate(screen.route)
                },
                onBack = {}
            )
        }

        composable(Screen.Pantalla3.route) {
            Pantalla3(
                changeScreen = { screen ->
                    navController.navigate(screen.route)
                },
                onBack = {}
            )
        }

        composable(Screen.Pantalla4.route) {
            Pantalla4(
                changeScreen = { screen ->
                    navController.navigate(screen.route)
                },
                onBack = {}
            )
        }

    }
}
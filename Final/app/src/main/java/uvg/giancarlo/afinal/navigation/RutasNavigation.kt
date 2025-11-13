package uvg.giancarlo.afinal.navigation

sealed class Screen(val route: String) {

    data object Pantalla1 : Screen("Pantalla1")

    data object Pantalla2 : Screen("Pantalla2")

    data object Pantalla3 : Screen("Pantalla3")

    data object Pantalla4 : Screen("Pantalla4")


}
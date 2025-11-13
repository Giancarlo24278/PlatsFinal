package uvg.giancarlo.afinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uvg.giancarlo.afinal.navigation.AppNavigation
import uvg.giancarlo.afinal.ui.theme.FinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalTheme {
                AppNavigation()
            }
        }
    }
}
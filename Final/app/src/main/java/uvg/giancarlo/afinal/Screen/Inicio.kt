package uvg.giancarlo.afinal.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.giancarlo.afinal.navigation.Screen

@Composable
fun Pantalla1(
    modifier: Modifier = Modifier,
    changeScreen: (Screen) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Título principal
            Text(
                text = "Crypt",
                fontSize = 50.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            // Descripción
            Text(
                text = "Hola Bienvenid@ al mercado crypto.\nAquí puedes visualizar las monedas que están disponibles.",
                fontSize = 18.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
                lineHeight = 26.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Botón morado inferior
            Button(
                onClick = { changeScreen(Screen.Pantalla2) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E44AD)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ver Crypto!",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { changeScreen(Screen.Pantalla3) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2c6a90)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ver sin Internet!",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


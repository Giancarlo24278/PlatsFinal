package uvg.giancarlo.afinal.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.giancarlo.afinal.navigation.Screen

@Composable
fun Pantalla4(
    modifier: Modifier = Modifier,
    changeScreen: (Screen) -> Unit,
    onBack: () -> Unit
) {
    // Barra superior
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "< Atrás",
            color = Color(0xFF6A1B9A),
            modifier = Modifier
                .clickable {
                    onBack()
                    changeScreen(Screen.Pantalla1)
                }
                .padding(8.dp)
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Perfil",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }

        IconButton(
            onClick = { changeScreen(Screen.Pantalla4) },
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Ir a Pantalla4",
                tint = Color(0xFF8E44AD)
            )
        }

        Spacer(modifier = Modifier.width(56.dp))
    }

    // Contenido principal
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Imagen o ícono de perfil
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "User Profile",
                modifier = Modifier.size(180.dp),
                tint = Color(0xFF666666)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Descripción
        Text(
            text = "Bienvenido a tu perfil.\nAquí puedes ir viendo cómo te va a ti y al mercado.",
            fontSize = 15.sp,
            color = Color(0xFF666666),
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Datos simulados del mercado
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Supply: 18,750,000",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Máximo Supply: 21,000,000",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Market Cap USD: 1,250,000,000,000",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

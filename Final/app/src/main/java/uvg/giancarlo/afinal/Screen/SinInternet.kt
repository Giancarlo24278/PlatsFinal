package uvg.giancarlo.afinal.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.giancarlo.afinal.navigation.Screen
import uvg.giancarlo.afinal.data.Crypto
import uvg.giancarlo.afinal.data.CryptoCache
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Pantalla3(
    modifier: Modifier = Modifier,
    changeScreen: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val cachedCryptos = remember { CryptoCache.getCryptos() }
    val lastUpdateTime = remember { CryptoCache.getLastUpdateTime() }
    val hasData = remember { CryptoCache.hasData() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header con botón de regreso
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
                    text = "Pantalla 3 - Modo Offline",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.width(56.dp))
        }

        // Contenido central
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (!hasData) {
                    // No hay datos en cache
                    Text(
                        text = "No hay datos guardados",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ve a Pantalla 2 para cargar datos primero",
                        fontSize = 14.sp,
                        color = Color(0xFF888888)
                    )
                } else {
                    // Mostrar datos guardados
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "📱 Datos guardados localmente",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF6A1B9A),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        cachedCryptos.forEach { crypto ->
                            CryptoCardOffline(crypto = crypto)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Fecha y hora de actualización
                        val formattedDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            .format(Date(lastUpdateTime))

                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Viendo data del $formattedDate",
                            fontSize = 14.sp,
                            color = Color(0xFF888888)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CryptoCardOffline(crypto: Crypto) {
    val price = crypto.priceUsd.toDoubleOrNull() ?: 0.0
    val change = crypto.changePercent24Hr.toDoubleOrNull() ?: 0.0

    val changeColor = if (change >= 0) Color(0xFF4CAF50) else Color(0xFFF44336)
    val arrow = if (change >= 0) "↑" else "↓"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = crypto.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = crypto.symbol,
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )
                }

                Text(
                    text = "🔌 Offline",
                    fontSize = 12.sp,
                    color = Color(0xFF888888)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Precio USD: $${String.format("%.2f", price)}",
                fontSize = 16.sp
            )
            Text(
                text = "Cambio 24h: $arrow ${String.format("%.2f", change)}%",
                fontSize = 16.sp,
                color = changeColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
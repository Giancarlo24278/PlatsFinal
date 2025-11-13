package uvg.giancarlo.afinal.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uvg.giancarlo.afinal.navigation.Screen
import uvg.giancarlo.afinal.data.Crypto
import uvg.giancarlo.afinal.data.CryptoCache
import uvg.giancarlo.afinal.network.ApiService

@Composable
fun Pantalla2(
    modifier: Modifier = Modifier,
    changeScreen: (Screen) -> Unit,
    onBack: () -> Unit
) {
    var screenState by remember { mutableStateOf("loading") }
    var cryptos by remember { mutableStateOf<List<Crypto>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    val apiService = remember { ApiService() }

    // Control de pantallas con delays y carga de API
    LaunchedEffect(Unit) {
        delay(1500) // "Loading..."
        screenState = "success"
        delay(1000) // "Success!"
        screenState = "crypto"

        // Llamada al API con KTOR
        // Llamada al API con KTOR
        scope.launch {
            try {
                val response = apiService.getCryptos()
                cryptos = response.data.take(3)
                CryptoCache.saveCryptos(cryptos)
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error al obtener datos"
                e.printStackTrace()
            }
        }
    }
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
                        text = "Datos",
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

            // Contenido central
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                when (screenState) {
                    "loading" -> Text("Loading...", fontSize = 22.sp, color = Color.Gray)
                    "success" -> Text(
                        "Success!",
                        fontSize = 24.sp,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )

                    "crypto" -> {
                        when {
                            errorMessage != null -> Text("Error: $errorMessage", color = Color.Red)
                            cryptos.isEmpty() -> Text(
                                "Cargando criptomonedas...",
                                color = Color.Gray
                            )

                            else -> {
                                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                    cryptos.forEach { crypto ->
                                        CryptoCard(crypto = crypto)
                                    }
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Actualmente estás viendo conectado a la red",
                            fontSize = 16.sp,
                            color = Color(0xFF666666)
                        )
                    }
                }

            }
        }
    }

    @Composable
    fun CryptoCard(crypto: Crypto) {
        // Convertimos valores
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
                Text("Nombre: ${crypto.name}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Símbolo: ${crypto.symbol}", fontSize = 16.sp)
                Text("Precio USD: $${String.format("%.2f", price)}", fontSize = 16.sp)
                Text(
                    "Cambio 24h: $arrow ${String.format("%.2f", change)}%",
                    fontSize = 16.sp,
                    color = changeColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }


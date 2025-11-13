package uvg.giancarlo.afinal.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import uvg.giancarlo.afinal.data.CryptoResponse

class ApiService {
    private val client = KtorClient.client

    suspend fun getCryptos(): CryptoResponse {
        return client.get("assets").body()
    }

    suspend fun getCryptoById(id: String): CryptoResponse {
        return client.get("assets/$id").body()
    }
}

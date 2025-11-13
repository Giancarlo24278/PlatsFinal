package uvg.giancarlo.afinal.data

data class CryptoResponse(
    val data: List<Crypto>
)

data class Crypto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val marketCapUsd: String?
)
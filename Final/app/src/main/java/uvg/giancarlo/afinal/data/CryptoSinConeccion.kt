package uvg.giancarlo.afinal.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

object CryptoCache {
    private var cachedCryptos = mutableStateOf<List<Crypto>>(emptyList())
    private var lastUpdateTime = mutableStateOf<Long>(0L)

    fun saveCryptos(cryptos: List<Crypto>) {
        cachedCryptos.value = cryptos
        lastUpdateTime.value = System.currentTimeMillis()
    }

    fun getCryptos(): List<Crypto> = cachedCryptos.value

    fun getLastUpdateTime(): Long = lastUpdateTime.value

    fun hasData(): Boolean = cachedCryptos.value.isNotEmpty()
}
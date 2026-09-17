package com.example.data.repository

import com.example.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NearbyPeer(
    val id: String,
    val name: String,
    val deviceModel: String,
    val signalStrength: Int, // 1..3
    val isAvailable: Boolean = true
)

sealed class P2PTransferStatus {
    object Idle : P2PTransferStatus()
    data class Scanning(val peersFound: List<NearbyPeer>) : P2PTransferStatus()
    data class Connecting(val peer: NearbyPeer) : P2PTransferStatus()
    data class Transferring(val bookTitle: String, val peer: NearbyPeer, val progress: Int) : P2PTransferStatus()
    data class Success(val message: String, val bookTitle: String) : P2PTransferStatus()
    data class Error(val message: String) : P2PTransferStatus()
}

class P2PManager {

    private val _status = MutableStateFlow<P2PTransferStatus>(P2PTransferStatus.Idle)
    val status = _status.asStateFlow()

    private val dummyPeers = listOf(
        NearbyPeer("peer_1", "Maria Da Silva (Klase 8B)", "Redmi 9A • Wi-Fi Direct", 3),
        NearbyPeer("peer_2", "João Pereira (Klase 7A)", "Samsung Galaxy A03 • Bluetooth", 2),
        NearbyPeer("peer_3", "Sebastião Ximenes (Klase 9C)", "Infinix Hot 12 • Wi-Fi Direct", 3),
        NearbyPeer("peer_4", "Filomena Dos Santos", "Tecno Spark 8 • Hotspot Local", 1)
    )

    suspend fun startDiscovery() {
        _status.value = P2PTransferStatus.Scanning(emptyList())
        delay(600)
        _status.value = P2PTransferStatus.Scanning(dummyPeers.take(2))
        delay(800)
        _status.value = P2PTransferStatus.Scanning(dummyPeers)
    }

    suspend fun sendBookToPeer(book: Book, peer: NearbyPeer) {
        _status.value = P2PTransferStatus.Connecting(peer)
        delay(900)

        for (progress in 10..100 step 15) {
            _status.value = P2PTransferStatus.Transferring(book.title, peer, progress)
            delay(250)
        }

        _status.value = P2PTransferStatus.Success(
            message = "Livru '${book.title}' haruka susesu ona ba ${peer.name}!",
            bookTitle = book.title
        )
    }

    suspend fun receiveBookFromPeer(peer: NearbyPeer, bookTitle: String, onReceiveComplete: suspend () -> Unit) {
        _status.value = P2PTransferStatus.Connecting(peer)
        delay(900)

        for (progress in 10..100 step 15) {
            _status.value = P2PTransferStatus.Transferring(bookTitle, peer, progress)
            delay(250)
        }

        onReceiveComplete()

        _status.value = P2PTransferStatus.Success(
            message = "Simu susesu livru '$bookTitle' husi ${peer.name}!",
            bookTitle = bookTitle
        )
    }

    fun reset() {
        _status.value = P2PTransferStatus.Idle
    }
}

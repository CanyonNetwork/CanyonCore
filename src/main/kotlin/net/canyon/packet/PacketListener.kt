package net.canyon.packet

import com.github.retrooper.packetevents.event.PacketListenerAbstract
import com.github.retrooper.packetevents.event.PacketListenerPriority
import com.github.retrooper.packetevents.event.PacketSendEvent
import java.util.concurrent.Executors


private val packetExecutor = Executors.newSingleThreadExecutor()

interface AsyncPacketListener {

    fun accept(event: PacketSendEvent)

}

abstract class AsyncPacketSendListener(private val packetPriority: PacketListenerPriority) : AsyncPacketListener, PacketListenerAbstract(packetPriority) {
    constructor() : this(PacketListenerPriority.NORMAL)

    abstract override fun accept(event: PacketSendEvent)

    override fun onPacketSend(event: PacketSendEvent) {
        val copy = event.clone()
        packetExecutor.execute {
            accept(copy)
            copy.cleanUp() // Clean up the packet to prevent memory leaks.
        }
    }
}

abstract class AsyncPacketReceiveListener(private val packetPriority: PacketListenerPriority) : AsyncPacketListener, PacketListenerAbstract(packetPriority) {
    constructor() : this(PacketListenerPriority.NORMAL)

    abstract override fun accept(event: PacketSendEvent)

    override fun onPacketSend(event: PacketSendEvent) {
        val copy = event.clone()
        packetExecutor.execute {
            accept(copy)
            copy.cleanUp() // Clean up the packet to prevent memory leaks.
        }
    }
}

/**
 * This class is used to identify the state of an executed [AsyncPacketListener].
 * It is suggested to cache the result of commands.
 */
open class ListenerResult(val success: Boolean, val message: String?) {

    companion object {
        val SUCCESS = ListenerResult(true, null)
        val FAILURE = ListenerResult(false, null)
    }

    class Success(message: String? = null) : ListenerResult(true, message)
    class Failure(message: String? = null) : ListenerResult(false, message)
}
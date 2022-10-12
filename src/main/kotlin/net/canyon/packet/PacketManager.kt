package net.canyon.packet

import com.github.retrooper.packetevents.PacketEvents
import com.github.retrooper.packetevents.event.PacketListenerAbstract

class PacketManager {

    init {
    }


    fun addListener(listener: PacketListenerAbstract) {
        PacketEvents.getAPI().eventManager.registerListener(listener)
    }


}
package net.canyon.packet

import com.github.retrooper.packetevents.PacketEvents
import com.github.retrooper.packetevents.event.PacketListenerAbstract
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTitle
import org.bukkit.entity.Player

class PacketManager {

    init {
    }


    fun addListener(listener: PacketListenerAbstract) {
        PacketEvents.getAPI().eventManager.registerListener(listener)
    }


    fun displayTitleTo(player: Player, title: String, subtitle: String, fadeIn: Int, stay: Int, fadeOut: Int) {
        val packetUser = PacketEvents.getAPI().playerManager.getUser(player)
        val titlePacket: WrapperPlayServerTitle = WrapperPlayServerTitle(WrapperPlayServerTitle.TitleAction.SET_TITLE, title, subtitle, null, fadeIn, stay, fadeOut)
        packetUser.sendPacket(titlePacket)
    }

}
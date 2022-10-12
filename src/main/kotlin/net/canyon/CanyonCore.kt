package net.canyon

import com.github.retrooper.packetevents.PacketEvents
import com.github.retrooper.packetevents.PacketEventsAPI
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder
import net.canyon.command.CommandManager
import net.canyon.command.CommandResult
import org.bukkit.plugin.java.JavaPlugin

object CanyonCore : JavaPlugin() {

    val commandManager: CommandManager = CommandManager()

    override fun onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this))
    }

    override fun onEnable() {

    }

    override fun onDisable() {

    }




}
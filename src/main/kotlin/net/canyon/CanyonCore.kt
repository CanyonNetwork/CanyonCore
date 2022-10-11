package net.canyon

import net.canyon.command.CommandManager
import net.canyon.command.CommandResult
import org.bukkit.plugin.java.JavaPlugin

object CanyonCore : JavaPlugin() {

    val commandManager: CommandManager = CommandManager()


    override fun onEnable() {
    }

    override fun onDisable() {

    }




}
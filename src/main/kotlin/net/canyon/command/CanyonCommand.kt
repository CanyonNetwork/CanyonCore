package net.canyon.command

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

abstract class CanyonCommand(val name: String, vararg aliases: String) {

    val aliases = aliases.toList()
    var permission: String = ""
    var usage: String = ""
    var description: String = ""


    abstract fun runPlayer(sender: Player, args: Array<out String>): CommandResult
    abstract fun runConsole(sender: ConsoleCommandSender, args: Array<out String>): CommandResult

    fun process(sender: CommandSender, args: Array<out String>): CommandResult {
        if (permission.isEmpty() || sender.hasPermission(permission)) {
            if (sender is Player) return runPlayer(sender, args)
            else if (sender is ConsoleCommandSender) return runConsole(sender, args)
        }
        return CommandResult.NoPermission()
    }


}
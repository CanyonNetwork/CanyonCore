package net.canyon.command

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

abstract class CanyonCommand(val name: String, vararg aliases: String) {

    private val aliases = aliases.toSet()
    private var permission = ""

    fun setPermission(permission: String) {
        this.permission = permission
    }

    abstract fun runPlayer(sender: Player, args: Array<String>): CommandResult
    abstract fun runConsole(sender: ConsoleCommandSender, args: Array<String>): CommandResult

    fun process(sender: CommandSender, args: Array<String>): CommandResult {
        if (permission.isEmpty() || sender.hasPermission(permission)) {
            if (sender is Player) return runPlayer(sender, args)
            else if (sender is ConsoleCommandSender) return runConsole(sender, args)
        }
        return CommandResult.NoPermission()
    }
}
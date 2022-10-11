package net.canyon.command

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

sealed class CommandManager {

    private val commands = mutableSetOf<CanyonCommand>()

    fun registerCommand(command: CanyonCommand) {
        commands.add(command)
    }

    

}

abstract class CanyonCommand(val name: String, vararg aliases: String) {

    private val aliases = aliases.toSet()
    private lateinit var permission: String

    fun setPermission(permission: String) {
        this.permission = permission
    }

    protected fun run(sender: CommandSender, args: Array<out String>) {
        if (sender.hasPermission(permission)) {
            execute(sender, args)
        } else {
            sender.sendMessage("§cYou do not have permission to execute this command.")
        }
    }

    abstract fun execute(sender: CommandSender, args: Array<out String>)
    private fun isConsole(sender: CommandSender) = sender is ConsoleCommandSender
    protected fun isPlayer(sender: CommandSender) = !isConsole(sender)
}
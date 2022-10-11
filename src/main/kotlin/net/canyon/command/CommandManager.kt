package net.canyon.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandManager {

    private val commands = mutableSetOf<CanyonCommand>()

    init {




        // Registering all commands
        for (command in commands) {
            Bukkit.getCommandMap().register("help", BukkitCommand(command))
        }
    }


    fun registerCommand(command: CanyonCommand) {
        commands.add(command)
    }

}

private class BukkitCommand(private val command: CanyonCommand): Command(command.name, command.description, command.description, command.aliases) {
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        val commandResult = command.process(sender, args)
        if (commandResult.success) return true
        return false
    }

}
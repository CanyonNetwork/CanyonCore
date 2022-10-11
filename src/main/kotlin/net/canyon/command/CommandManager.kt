package net.canyon.command

class CommandManager {

    private val commands = mutableSetOf<CanyonCommand>()

    init {

    }


    fun registerCommand(command: CanyonCommand) {
        commands.add(command)
    }

}

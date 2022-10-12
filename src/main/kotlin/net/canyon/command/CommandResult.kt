package net.canyon.command

/**
 * This class is used to identify the state of an executed [CanyonCommand].
 * It is suggested to cache the result of commands.
 */
open class CommandResult(val success: Boolean, val message: String?) {

    companion object {
        val SUCCESS = CommandResult(true, null)
        val FAILURE = CommandResult(false, null)
    }

    class Success(message: String? = null) : CommandResult(true, message)
    class Failure(message: String? = null) : CommandResult(false, message)
    class NoPermission(message: String? = null) : CommandResult(false, message)
}
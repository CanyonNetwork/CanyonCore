package net.canyon.utils

import net.kyori.adventure.text.minimessage.MiniMessage

private sealed class MessageContainer {

    companion object {
        private val messages = mutableMapOf<String, String>()
        private val minimessage: MiniMessage = MiniMessage.miniMessage()

        fun addMessage(key: String, message: String) {
            messages[key] = message
        }

        fun removeMessage(key: String) {
            messages.remove(key)
        }

        fun getRawMessage(key: String) = messages[key]
        fun get(key: String) = minimessage.deserialize(messages[key] ?: "<red>Unknown Input")
    }

}
package br.com.ardc.springsandbox.domain

/**
 * A representation of an User for messaging needs.
 * @param userName the userName (for logins)
 * @param displayName the display name (for displaying)
 */
data class User(val userName: String, val displayName: String?)

/**
 * A representation of a Message sent between two users.
 * @param sender the user sending the message
 * @param recipient the user that should receive the message
 * @param content the contents of the string itself, in a string format
 * @param isRead flag indicating if the message was read by the recipient or not, once this is true the message can no longer be deleted or editted
 */
data class Message(val sender: User, val recipient: User, val content: String, val isRead: Boolean)

/**
 * Creates a new message using the selected user as the sender.
 */
fun User.createMessageTo(recipient: User, content: String): Message {
    return Message(this, recipient, content, false)
}
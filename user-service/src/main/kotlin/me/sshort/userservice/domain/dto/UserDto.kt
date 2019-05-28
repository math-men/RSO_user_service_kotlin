package me.sshort.userservice.domain.dto

import me.sshort.userservice.domain.model.User

data class UserDto(
    val username: String,
    val password: String? = null,
    val email: String?
) {
    fun toUser() = User(
        username = username!!,
        password = password!!,
        email = email
    )

    fun fromUser(user: User) = UserDto(
        username = user.username,
        email = user.email
    )
}
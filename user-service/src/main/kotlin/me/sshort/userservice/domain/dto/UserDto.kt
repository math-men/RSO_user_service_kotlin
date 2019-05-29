package me.sshort.userservice.domain.dto

import me.sshort.userservice.domain.model.User
import javax.validation.constraints.NotNull

data class UserDto(
    @NotNull val username: String,
    @NotNull val password: String? = null,
    @NotNull val email: String?
) {
    fun toUser() = User(
        username = username!!,
        password = password!!,
        email = email
    )

    companion object {
        fun fromUser(user: User) = UserDto(
            username = user.username,
            email = user.email
        )
    }
}
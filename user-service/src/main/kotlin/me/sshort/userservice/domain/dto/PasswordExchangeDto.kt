package me.sshort.userservice.domain.dto

data class PasswordExchangeDto(
    val oldPassword: String,
    val newPassword: String
)
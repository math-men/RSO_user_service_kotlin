package me.sshort.userservice.domain.dto

data class LinkInfoDto(
    val clicks: Long,
    val shortenedUrl: String,
    val url: String,
    val expirationDate: String
)
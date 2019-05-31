package me.sshort.userservice.domain.dto

import me.sshort.userservice.domain.model.UserLinkAssignment


data class LinkMappingDto(
    val url: String,
    val shortenedUrl: String
) {
    fun toUserLinkAssignment(userId: Long) = UserLinkAssignment(
        userId = userId,
        url = url,
        shortenedUrl = shortenedUrl
    )

    companion object {
        fun fromUserLinkAssignment(userLinkAssignment: UserLinkAssignment) = LinkMappingDto(
            url = userLinkAssignment.url,
            shortenedUrl = userLinkAssignment.shortenedUrl
        )
    }
}

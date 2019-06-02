package me.sshort.userservice.service

import me.sshort.userservice.adapter.LinkServiceAdapter
import me.sshort.userservice.domain.dto.*
import me.sshort.userservice.repository.LinkRepository
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class LinkService(
    val linkRepository: LinkRepository,
    val linkServiceAdapter: LinkServiceAdapter
) {

    private val TTL_ANONYMOUS_MS = 604_800_000
    private val TTL_PREMIUM_MS = Int.MAX_VALUE

    fun create(linkDto: LinkDto): LinkMappingDto {

        val authentication = SecurityContextHolder.getContext().authentication
        val isAnonymous = authentication is AnonymousAuthenticationToken
        val ttl = if (isAnonymous) TTL_ANONYMOUS_MS else TTL_PREMIUM_MS
        val init = LinkInitDto(linkDto.url, ttl)
        val mapping = linkServiceAdapter.createLink(init)

        if (!isAnonymous) {
            val user = authentication.principal as UserDto
            val userId = user.userId
            linkRepository.save(mapping.toUserLinkAssignment(userId!!))
        }


        return mapping
    }

    fun findAllUserLinks(userId: Long): List<LinkMappingDto> {
        val urlsMappings = linkRepository.findByUserId(userId)

        return urlsMappings.map {
            LinkMappingDto.fromUserLinkAssignment(it)
        }.toList()
    }

    fun findLinkInfo(shortenedUrl: String): LinkInfoDto {
        val clicksDto = linkServiceAdapter.findLinkInfo(shortenedUrl)
        val mapping = linkRepository.findByShortenedUrl(shortenedUrl)

        val calendar = Calendar.getInstance()
        calendar.time = mapping.createdAt
        calendar.add(Calendar.MILLISECOND, Int.MAX_VALUE)


        return LinkInfoDto(clicksDto.clicks, shortenedUrl, mapping.url, calendar.time.toString())
    }

}

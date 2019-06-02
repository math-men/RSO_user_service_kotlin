package me.sshort.userservice.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import me.sshort.userservice.domain.dto.LinkInfoDto
import me.sshort.userservice.domain.dto.LinkInitDto
import me.sshort.userservice.domain.dto.LinkMappingDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LinkServiceAdapter(
    objectMapper: ObjectMapper
) {

    @Value("\${linksvc.url}")
    lateinit var linkServiceUrl: String

    val BASE_URI = "/api/link"
    val restTemplate: RestTemplate = RestTemplate()

    init {
        restTemplate.errorHandler = LinkServiceResponseErrorHandler(objectMapper)
    }

    fun createLink(init: LinkInitDto): LinkMappingDto {
        val uri = "$linkServiceUrl/links"
        return restTemplate.postForObject(uri, init, LinkMappingDto::class.java)
            ?: throw IllegalStateException("Link creation failed")
    }

    fun findLinkInfo(shortenedUrl: String): LinkInfoDto {
        val uri = "$linkServiceUrl/links/$shortenedUrl"
        return restTemplate.getForObject(uri, LinkInfoDto::class.java)
            ?: throw IllegalStateException("Fetching link info failed")
    }
}
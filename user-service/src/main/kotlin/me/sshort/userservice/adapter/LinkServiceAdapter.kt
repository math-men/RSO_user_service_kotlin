package me.sshort.userservice.adapter

import me.sshort.userservice.domain.dto.LinkInfoDto
import me.sshort.userservice.domain.dto.LinkInitDto
import me.sshort.userservice.domain.dto.LinkMappingDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LinkServiceAdapter {
    @Value("linksvc.url")
    lateinit var url: String

    fun createLink(init: LinkInitDto): LinkMappingDto {
        val uri = "$url/api/link"
        val restTemplate = RestTemplate()
        // TODO
//        return restTemplate.postForObject(uri, init, LinkMappingDto::class.java)
        // TODO Verify POST result (e.g. link service may fail)
        return LinkMappingDto(init.url, "abcabc123")
    }

    fun findLinkInfo(shortenedUrl: String): LinkInfoDto {
        val uri = "$url/api/link/$shortenedUrl"
        val restTemplate = RestTemplate()
        // TODO
        return restTemplate.getForObject(uri, LinkInfoDto::class.java)
        // TODO Verify GET result (e.g. shortened URL not exists)
        return LinkInfoDto(10)
    }

}
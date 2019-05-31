package me.sshort.userservice.adapter

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
//        return restTemplate.postForObject(uri, init, LinkMappingDto::class.java)
        return LinkMappingDto(init.url, "sshort.me/dupa")
    }

}
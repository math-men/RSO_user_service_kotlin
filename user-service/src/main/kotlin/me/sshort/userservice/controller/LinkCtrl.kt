package me.sshort.userservice.controller

import me.sshort.userservice.domain.dto.*
import me.sshort.userservice.service.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/link")
class LinkCtrl(
    val linkService: LinkService
) {
    @PostMapping
    fun create(
        @RequestBody @Validated linkDto: LinkDto
    ): ResponseEntity<LinkMappingDto> {
        val linkMapping = linkService.create(linkDto)

        return ResponseEntity.ok().body(linkMapping)
    }

    @GetMapping("/{shortenedUrl}")
    fun findLinkInfo(@PathVariable shortenedUrl: String): ResponseEntity<LinkInfoDto> {
        val linkInfo = linkService.findLinkInfo(shortenedUrl)

        return ResponseEntity.ok().body(linkInfo)
    }

    @GetMapping
    fun findAllUserLinks(): ResponseEntity<List<LinkMappingDto>> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDto
        val userId = user.userId!!
        val allUserLinks = linkService.findAllUserLinks(userId)

        return ResponseEntity.ok().body(allUserLinks)
    }
}

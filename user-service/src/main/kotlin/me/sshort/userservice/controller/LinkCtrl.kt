package me.sshort.userservice.controller

import me.sshort.userservice.configuration.security.UserPrincipal
import me.sshort.userservice.domain.dto.LinkDto
import me.sshort.userservice.domain.dto.LinkInfoDto
import me.sshort.userservice.domain.dto.LinkMappingDto
import me.sshort.userservice.service.LinkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated

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

        // TODO
        return ResponseEntity.ok().body(null)
    }

    @GetMapping
    fun findAllUserLinks(): ResponseEntity<List<LinkMappingDto>> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        val userId = user.userId

        // TODO
        return ResponseEntity.ok().body(null)
    }
}
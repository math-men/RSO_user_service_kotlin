package me.sshort.userservice.repository

import me.sshort.userservice.domain.dto.LinkMappingDto
import me.sshort.userservice.domain.model.UserLinkAssignment
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<UserLinkAssignment, Long> {

    fun findByUserId(userId: Long): List<LinkMappingDto>
}

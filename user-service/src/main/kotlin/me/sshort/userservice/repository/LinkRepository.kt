package me.sshort.userservice.repository

import me.sshort.userservice.domain.model.UserLinkAssignment
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<UserLinkAssignment, Long> {

}

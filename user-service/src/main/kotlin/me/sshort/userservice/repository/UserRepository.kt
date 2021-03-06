package me.sshort.userservice.repository

import me.sshort.userservice.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun existsUserByUsername(username: String): Boolean
}
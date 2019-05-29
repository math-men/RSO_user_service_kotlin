package me.sshort.userservice.configuration.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class UserPrincipal(
    username: String,
    password: String,
    authorities: Collection<GrantedAuthority>,
    val userId: Long
): User(username, password, authorities)
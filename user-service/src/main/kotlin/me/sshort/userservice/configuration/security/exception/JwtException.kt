package me.sshort.userservice.configuration.security.exception

import org.springframework.security.core.AuthenticationException

class JwtException (
    message: String
): AuthenticationException(message)

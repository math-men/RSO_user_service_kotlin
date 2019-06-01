package me.sshort.userservice.adapter.exception

import org.springframework.http.HttpStatus

class LinkServiceException(
    val httpStatus: HttpStatus,
    message: String
) : RuntimeException(message)
package me.sshort.userservice.service.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class AbstractException (
    val httpStatus: HttpStatus,
    message: String
): RuntimeException(message)
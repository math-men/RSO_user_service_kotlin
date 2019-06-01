package me.sshort.userservice.controller

import me.sshort.userservice.adapter.exception.LinkServiceException
import me.sshort.userservice.domain.dto.ErrorDto
import me.sshort.userservice.service.exception.AbstractException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ControllerAdvice
class ExceptionHandler {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
    val INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error"

    @ExceptionHandler
    fun handleAbstractException(exception: AbstractException): ResponseEntity<ErrorDto> {
        log.error("Exception occured!", exception)
        return ResponseEntity
            .status(exception.httpStatus)
            .body(ErrorDto(exception.message))
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception): ResponseEntity<ErrorDto> {
        log.error("Exception occured!", exception)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDto(INTERNAL_SERVER_ERROR_MESSAGE))
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorDto> {
        log.error("Exception occured!", exception)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorDto(exception.message))
    }

    @ExceptionHandler
    fun handleLinkServiceException(exception: LinkServiceException): ResponseEntity<ErrorDto> {
        log.error("Exception occured!", exception)
        return ResponseEntity
            .status(exception.httpStatus)
            .body(ErrorDto(exception.message))
    }

}
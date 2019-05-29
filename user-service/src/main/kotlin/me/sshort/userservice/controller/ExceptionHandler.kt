package me.sshort.userservice.controller

import me.sshort.userservice.domain.dto.ErrorDto
import me.sshort.userservice.service.exception.AbstractException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ControllerAdvice
class ExceptionHandler {

    val INTERNAL_SERVER_ERROR_MESSAGE = "Wewnętrzny błąd serwera"

    @ExceptionHandler
    fun handleAbstractException(exception: AbstractException): ResponseEntity<ErrorDto> {
        return ResponseEntity
            .status(exception.httpStatus)
            .body(ErrorDto(exception.message))
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception): ResponseEntity<ErrorDto> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDto(INTERNAL_SERVER_ERROR_MESSAGE))
    }

}
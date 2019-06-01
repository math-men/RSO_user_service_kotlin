package me.sshort.userservice.service.exception

import org.springframework.http.HttpStatus

class UserAlreadyExistsException
    : AbstractException(HttpStatus.CONFLICT, "User already exists")
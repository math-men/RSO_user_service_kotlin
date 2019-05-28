package me.sshort.userservice.controller

import me.sshort.userservice.domain.dto.UserDto
import me.sshort.userservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserCtrl(
    val userService: UserService
) {

    @PostMapping
    fun signUp(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.registerUser(userDto)

        return ResponseEntity.ok("utworzone")
    }

}
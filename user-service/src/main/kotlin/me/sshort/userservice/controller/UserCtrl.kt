package me.sshort.userservice.controller

import me.sshort.userservice.domain.dto.UserDto
import me.sshort.userservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/user")
class UserCtrl(
    val userService: UserService
) {

    @PostMapping
    fun signUp(
        @RequestBody @Validated userDto: UserDto,
        uriComponentsBuilder: UriComponentsBuilder,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Void> {
        val userId = userService.registerUser(userDto)

        return ResponseEntityFactory.created(httpServletRequest, uriComponentsBuilder, userId!!)
    }

    @GetMapping("/{id}")
    fun fetchUser(@PathVariable id: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok().body(userService.findUser(id))

    }

}
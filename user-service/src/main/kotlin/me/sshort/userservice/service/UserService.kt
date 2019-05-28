package me.sshort.userservice.service

import me.sshort.userservice.domain.dto.UserDto
import me.sshort.userservice.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun registerUser(userDto: UserDto) {
        val userCopy = userDto.copy(password = bCryptPasswordEncoder.encode(userDto.password))
        userRepository.save(userCopy.toUser())
    }

}
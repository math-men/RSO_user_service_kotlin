package me.sshort.userservice.service

import me.sshort.userservice.domain.dto.UserDto
import me.sshort.userservice.repository.UserRepository
import me.sshort.userservice.service.exception.UserAlreadyExistsException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(
    val userRepository: UserRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun registerUser(userDto: UserDto): Long? {
        val userCopy = userDto.copy(password = bCryptPasswordEncoder.encode(userDto.password))

        if (userRepository.existsUserByUsername(userDto.username)) {
            throw UserAlreadyExistsException()
        }

        val userId = userRepository.save(userCopy.toUser()).id

        return userId
    }

    fun findUser(id: Long): UserDto {
        val user = userRepository.findOne(id) ?: throw RuntimeException()

        return UserDto.fromUser(user = user)
    }


    // --- EXTENSION FUNCTIONS FOR REPOSITORY ---
    fun <T, ID> JpaRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)

}
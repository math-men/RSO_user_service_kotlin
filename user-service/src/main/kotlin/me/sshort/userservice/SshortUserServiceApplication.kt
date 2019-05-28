package me.sshort.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class SshortUserServiceApplication

fun main(args: Array<String>) {
    runApplication<SshortUserServiceApplication>(*args)
}



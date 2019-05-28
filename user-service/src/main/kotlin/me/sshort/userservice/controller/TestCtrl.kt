package me.sshort.userservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class TestCtrl {

    @GetMapping
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }

}
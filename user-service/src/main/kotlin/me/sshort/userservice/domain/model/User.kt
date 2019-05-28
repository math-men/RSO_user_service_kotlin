package me.sshort.userservice.domain.model

import javax.persistence.*

@Entity
@Table(name = "USERS")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val username: String,

    @Column
    val password: String,

    @Column
    val email: String?
)
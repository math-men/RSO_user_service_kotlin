package me.sshort.userservice.domain.model

import javax.persistence.*

@Entity
@Table(name = "USERS_URLS")
data class UserLinkAssignment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "USER_ID")
    val userId: Long,

    @Column(name = "URL")
    val url: String,

    @Column(name = "SHORTENED_URL")
    val shortenedUrl: String
)

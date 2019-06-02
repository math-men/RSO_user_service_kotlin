package me.sshort.userservice.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "USERS_URLS")
@EntityListeners(AuditingEntityListener::class)
data class UserLinkAssignment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "USER_ID")
    val userId: Long,

    @Column(name = "URL")
    val url: String,

    @Column(name = "SHORTENED_URL")
    val shortenedUrl: String,

    @Column(name = "CREATED_AT")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date? = null
)

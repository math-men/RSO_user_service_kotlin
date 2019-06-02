package me.sshort.userservice.configuration.persistence

import org.springframework.data.domain.AuditorAware
import java.util.*


class CustomAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("")
    }

}
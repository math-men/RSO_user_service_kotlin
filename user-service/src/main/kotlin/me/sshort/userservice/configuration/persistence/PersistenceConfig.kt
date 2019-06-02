package me.sshort.userservice.configuration.persistence

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
class PersistenceConfig {

    @Bean
    fun auditorProvider(): CustomAuditorAware {
        return CustomAuditorAware()
    }

}
package me.sshort.userservice.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class LinkServiceAdapterConfiguration(
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.errorHandler = LinkServiceResponseErrorHandler(objectMapper)
        return restTemplate
    }

}
package me.sshort.userservice.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import me.sshort.userservice.adapter.exception.LinkServiceException
import me.sshort.userservice.domain.dto.ErrorDto
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import java.io.InputStream

class LinkServiceResponseErrorHandler(
    private val objectMapper: ObjectMapper
) : DefaultResponseErrorHandler() {

    override fun handleError(response: ClientHttpResponse) {
        val body: InputStream = response.body
        val errorDto = objectMapper.readValue(body, ErrorDto::class.java)

        throw LinkServiceException(response.statusCode, errorDto.message!!)
    }
}
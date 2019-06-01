package me.sshort.userservice.configuration.security

import com.fasterxml.jackson.databind.ObjectMapper
import me.sshort.userservice.domain.dto.ErrorDto
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtAuthenticationEntryPoint(
    val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        e: AuthenticationException
    ) {
        httpServletResponse.contentType = "application/json"
        httpServletResponse.status = HttpServletResponse.SC_UNAUTHORIZED
        objectMapper.writeValue(httpServletResponse.writer, ErrorDto("Unauthorized access"))
    }

}
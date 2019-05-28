package me.sshort.userservice.configuration.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import me.sshort.userservice.domain.dto.UserDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    objectMapper_: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    val authenticationManager_: AuthenticationManager = authenticationManager
    val objectMapper = objectMapper_


    init {
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL)
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        val userDto: UserDto =
            objectMapper.readValue(request.inputStream, UserDto::class.java)

        val username = userDto.username
        val password = userDto.password
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)

        return authenticationManager_.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain, authentication: Authentication
    ) {
        val user = authentication.principal as User

        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        val token = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
            .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
            .setIssuer(SecurityConstants.TOKEN_ISSUER)
            .setAudience(SecurityConstants.TOKEN_AUDIENCE)
            .setSubject(user.username)
            .setExpiration(Date(System.currentTimeMillis() + 864000000))
            .compact()

        //TODO zwracac w ciele token
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token)
    }
}
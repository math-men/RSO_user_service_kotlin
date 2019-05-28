@file:Suppress("DEPRECATION")

package me.sshort.userservice.configuration.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthorizationFilter(
    authenticationManager: AuthenticationManager
) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader(SecurityConstants.TOKEN_HEADER)

        if (header.isBlank() || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        val authentication = getAuthentication(request)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(SecurityConstants.TOKEN_HEADER)
        if (token.isNotEmpty()) {
            try {
                val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

                val parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token.replace("Bearer ", ""))

                val username = parsedToken
                    .body
                    .subject

                if (username.isNotEmpty()) {
                    return UsernamePasswordAuthenticationToken(username, null, emptyList())
                }
            } catch (exception: ExpiredJwtException) {
                //TODO
            } catch (exception: UnsupportedJwtException) {
                //TODO
            } catch (exception: MalformedJwtException) {
                //TODO
            } catch (exception: SignatureException) {
                //TODO
            } catch (exception: IllegalArgumentException) {
                //TODO
            }

        }

        return null
    }

}
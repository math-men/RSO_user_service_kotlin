package me.sshort.userservice.configuration.security

object SecurityConstants {

    val AUTH_LOGIN_URL = "/api/user/token"

    val JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y\$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf"

    val TOKEN_HEADER = "Authorization"
    val TOKEN_PREFIX = "Bearer "
    val TOKEN_TYPE = "JWT"
    val TOKEN_ISSUER = "secure-api"
    val TOKEN_AUDIENCE = "secure-app"
}
package me.sshort.userservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletRequest


class ResponseEntityFactory() {

    companion object {

        fun <T> created(
            httpServletRequest: HttpServletRequest,
            uriComponentsBuilder: UriComponentsBuilder,
            objectId: Long
        ): ResponseEntity<T> {

            return created(httpServletRequest.getRequestURI(), uriComponentsBuilder, objectId.toString())
        }

        fun <T> created(
            baseUri: String,
            uriComponentsBuilder: UriComponentsBuilder,
            objectId: String
        ): ResponseEntity<T> {

            return ResponseEntity.created(uri(baseUri, uriComponentsBuilder, objectId)).build()
        }

        private fun uri(
            baseUri: String,
            uriComponentsBuilder: UriComponentsBuilder,
            objectId: String
        ): URI {
            uriComponentsBuilder
                .path("/$baseUri/{id}")
            return uriComponentsBuilder.buildAndExpand(objectId).toUri()
        }

        fun noContent(): ResponseEntity<Void> {
            return ResponseEntity.noContent().build()
        }

    }

}
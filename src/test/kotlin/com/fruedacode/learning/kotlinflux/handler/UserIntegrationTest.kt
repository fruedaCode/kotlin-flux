package com.fruedacode.learning.kotlinflux.handler

import com.fruedacode.learning.kotlinflux.model.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.test.test

class UserIntegrationTest : AbstractIntegrationTests(){

    @Test
    fun `request all users`() {
        client.get().uri("/user").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux<User>()
                .test()
                .consumeNextWith {
                    assertEquals(users[0], it)
                }
                .consumeNextWith {
                    assertEquals(users[1], it)
                }
                .verifyComplete()
    }
}
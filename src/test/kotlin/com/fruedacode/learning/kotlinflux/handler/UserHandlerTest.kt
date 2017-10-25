package com.fruedacode.learning.kotlinflux.handler

import com.fruedacode.learning.kotlinflux.model.User
import com.fruedacode.learning.kotlinflux.repository.UserRepository
import com.fruedacode.learning.kotlinflux.web.handler.UserHandler
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.reactive.function.server.EntityResponse
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier


class UserHandlerTest: AbstractTest() {

    @Autowired
    lateinit var userHandler: UserHandler

    @MockBean
    lateinit var userRepository: UserRepository


    @Test
    fun `should return all usersa`(){
        val serverRequest = mock(ServerRequest::class.java)
        val expedtedUser = listOf(
                User(name = "Fernando", email = "fernando@mail.com"),
                User(name = "Roberto", email = "roberto@mail.com"))

        given(userRepository.findAll()).willReturn(Flux.fromIterable(expedtedUser))

        StepVerifier.create(userHandler.findAll(serverRequest))
                .expectNextMatches {
                    val entityResponse = it as EntityResponse<Flux<User>>
                    expedtedUser == entityResponse.entity().collectList().block()
                }
                .verifyComplete()

    }
}
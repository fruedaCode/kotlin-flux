package com.fruedacode.learning.kotlinflux.handler

import com.fruedacode.learning.kotlinflux.model.Role
import com.fruedacode.learning.kotlinflux.model.User
import com.fruedacode.learning.kotlinflux.repository.RoleRepository
import com.fruedacode.learning.kotlinflux.repository.UserRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.dropCollection
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class AbstractIntegrationTests {

    @LocalServerPort
    var port: Int? = null

    lateinit var client: WebClient

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var mongoTemplate: ReactiveMongoTemplate


    val roles = listOf(
            Role(name = "CUSTOMER"),
            Role(name = "DRIVER")
    )
    val users = listOf(
            User(name = "Fernando", email = "fernando@mail.com"),
            User(name = "Roberto", email = "roberto@mail.com")
    )

    @BeforeAll
    fun setup() {
        client = WebClient.create("http://localhost:$port")

        userRepository.saveAll(users).then().block()
        roleRepository.saveAll(roles).then().block()

    }
    @AfterAll
    fun clean(){
        mongoTemplate.dropCollection(Role::class).block()
    }

}
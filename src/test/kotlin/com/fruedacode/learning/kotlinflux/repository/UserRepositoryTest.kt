package com.fruedacode.learning.kotlinflux.repository

import com.fruedacode.learning.kotlinflux.handler.AbstractTest
import com.fruedacode.learning.kotlinflux.model.Role
import com.fruedacode.learning.kotlinflux.model.User
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.dropCollection
import reactor.test.StepVerifier

class UserRepositoryTest: AbstractTest(){

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var mongoTemplate: ReactiveMongoTemplate

    private val users = listOf(
            User(name = "Fernando", email = "fernando@mail.com"),
            User(name = "Roberto", email = "roberto@mail.com")
    )

    private val roles = listOf(
            Role(name = "CUSTOMER"),
            Role(name = "DRIVER")
    )

    @BeforeEach
    fun setUp(){
        userRepository.saveAll(users).then().block()
    }

    @AfterEach
    fun cleanUp(){
       mongoTemplate.dropCollection(User::class).block()
    }

    @Test
    fun `should get one user`(){
        StepVerifier
                .create(userRepository.findByEmail(users[0].email))
                .expectNextMatches { dbUser -> users.find { it.email == dbUser.email } != null }
                .verifyComplete()
    }

    @Test
    fun `should return all the users`(){
        StepVerifier
                .create(userRepository.findAll())
                .expectNextMatches { dbUser ->
                    users.find { it.email == dbUser.email } != null
                }
                .expectNextMatches { dbUser ->
                    users.find { it.email == dbUser.email } != null
                }
                .verifyComplete()
    }

    @Test
    fun `should add a role to a user`(){
        var role = roles[0]
        val user = users[0]

        user.roles.add(roleRepository.save(role).block()!!)

        StepVerifier.create(userRepository.save(user))
                .expectNextMatches { dbUser ->
                    dbUser.roles.find { it.name == role.name } != null
                }
                .verifyComplete()

    }

    @Test
    fun `should fail if users exists`(){
        val existingUser = User(email = users[0].email)

        StepVerifier
                .create(userRepository.save(existingUser))
                .expectNext(existingUser)
                .verifyComplete()
    }
}
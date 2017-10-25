package com.fruedacode.learning.kotlinflux.repository

import com.fruedacode.learning.kotlinflux.handler.AbstractTest
import com.fruedacode.learning.kotlinflux.model.Role
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.dropCollection
import reactor.test.StepVerifier

class RoleRepositoryTest: AbstractTest(){

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var mongoTemplate: ReactiveMongoTemplate


    private val roles = listOf(
            Role(name = "CUSTOMER"),
            Role(name = "DRIVER")
    )

    @BeforeEach
    fun setUp(){
        roleRepository.saveAll(roles).then().block()
    }

    @AfterEach
    fun cleanUp(){
       mongoTemplate.dropCollection(Role::class).block()
    }

    @Test
    fun `should get the role`(){

        StepVerifier.create(roleRepository.findByName(roles[0].name))
                .assertNext { dbRole ->
                    roles.find { dbRole.name == it.name } != null
                }
                .verifyComplete()
    }

    @Test
    fun `should return all the roles`(){
        StepVerifier.create(roleRepository.findAll())
                .assertNext { dbRole ->
                    roles.find { it.name == dbRole.name } != null
                }
                .assertNext { dbRole ->
                    roles.find { it.name == dbRole.name } != null
                }
                .verifyComplete()
    }

}
package com.fruedacode.learning.kotlinflux.repository

import com.fruedacode.learning.kotlinflux.model.Role
import com.fruedacode.learning.kotlinflux.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RoleRepository : ReactiveCrudRepository<Role, String>{
    fun findByName(name : String): Mono<Role>
}
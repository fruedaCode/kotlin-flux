package com.fruedacode.learning.kotlinflux.web.handler

import com.fruedacode.learning.kotlinflux.model.Role
import com.fruedacode.learning.kotlinflux.repository.RoleRepository
import com.fruedacode.learning.kotlinflux.util.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono

@Component
class RoleHandler(private val roleRepository: RoleRepository){
    fun findOne(req: ServerRequest) = ok().json().body(roleRepository.findById(req.pathVariable("id")))
    fun findAll(req: ServerRequest) = ok().json().body(roleRepository.findAll())
    fun save(req: ServerRequest) = ok().json().body(roleRepository.save(req.bodyToMono<Role>().block()!!))
}
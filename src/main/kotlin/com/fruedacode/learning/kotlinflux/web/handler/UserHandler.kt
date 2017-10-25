package com.fruedacode.learning.kotlinflux.web.handler

import com.fruedacode.learning.kotlinflux.model.User
import com.fruedacode.learning.kotlinflux.repository.UserRepository
import com.fruedacode.learning.kotlinflux.util.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono

@Component
class UserHandler (private val userRepository: UserRepository){
    fun findOne(req: ServerRequest) = ok().json().body(userRepository.findById(req.pathVariable("id")))
    fun findAll(req: ServerRequest) = ok().json().body(userRepository.findAll())
    fun save(req: ServerRequest) = ok().json().body(userRepository.save(req.bodyToMono<User>().block()!!))
}
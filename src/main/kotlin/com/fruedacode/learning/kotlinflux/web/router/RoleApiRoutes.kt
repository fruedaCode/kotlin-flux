package com.fruedacode.learning.kotlinflux.web.router

import com.fruedacode.learning.kotlinflux.web.handler.RoleHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RoleApiRoutes(private val roleHandler: RoleHandler){

    @Bean
    fun roleRouter() = router {
        (accept(MediaType.APPLICATION_JSON) and "/role").nest {
            GET("/", roleHandler::findAll)
            GET("/{id}", roleHandler::findOne)
            POST("/", roleHandler::save)
        }
    }
}
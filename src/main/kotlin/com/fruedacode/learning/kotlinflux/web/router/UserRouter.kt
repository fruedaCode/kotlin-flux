package com.fruedacode.learning.kotlinflux.web.router

import com.fruedacode.learning.kotlinflux.web.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class UserApiRoutes(private val userHandler: UserHandler){

    @Bean
    fun userRouter() = router {
        (accept(MediaType.APPLICATION_JSON) and "/user").nest {
            GET("/", userHandler::findAll)
            GET("/{id}", userHandler::findOne)
            POST("/", userHandler::save)
        }
    }
}
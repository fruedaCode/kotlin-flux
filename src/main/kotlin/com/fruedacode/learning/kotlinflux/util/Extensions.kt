package com.fruedacode.learning.kotlinflux.util

import org.springframework.boot.SpringApplication
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.net.URI
import java.util.*
import kotlin.reflect.KClass

// ----------------------
// Spring Boot extensions
// ----------------------

fun run(type: KClass<*>, vararg args: String) = SpringApplication.run(type.java, *args)

// -------------------------
// Spring WebFlux extensions
// -------------------------

fun ServerResponse.BodyBuilder.json() = contentType(MediaType.APPLICATION_JSON_UTF8)

fun ServerResponse.BodyBuilder.xml() = contentType(MediaType.APPLICATION_XML)

fun ServerResponse.BodyBuilder.html() = contentType(MediaType.TEXT_HTML)

fun permanentRedirect(uri: String) = ServerResponse.permanentRedirect(URI(uri)).build()

fun seeOther(uri: String) = ServerResponse.seeOther(URI(uri)).build()
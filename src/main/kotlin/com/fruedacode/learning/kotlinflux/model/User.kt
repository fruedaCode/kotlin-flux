package com.fruedacode.learning.kotlinflux.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
        @Id val id: String? = null,
        @Indexed(unique = true) val email: String = "",
        val password: String = "",
        val name: String = "",
        @DBRef val roles: MutableList<Role> = mutableListOf())


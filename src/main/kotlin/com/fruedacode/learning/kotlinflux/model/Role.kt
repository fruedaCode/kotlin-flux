package com.fruedacode.learning.kotlinflux.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Role(
        @Id val id: String? = null,
        @Indexed(unique = true) val name: String,
        val description : String = ""
)
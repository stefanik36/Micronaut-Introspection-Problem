package com.example

@io.micronaut.core.annotation.Introspected
data class SubModel(
    @field:javax.validation.constraints.Email
    val email: String
)
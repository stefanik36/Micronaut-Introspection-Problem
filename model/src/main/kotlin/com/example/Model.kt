package com.example

@io.micronaut.core.annotation.Introspected
data class Model(
    @field:javax.validation.Valid
    val subModel: SubModel
)
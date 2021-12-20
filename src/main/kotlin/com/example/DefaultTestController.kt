package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/test")
@Validated
class DefaultTestController {

    @Post
    fun create(@Body @Valid model: Model): HttpResponse<Unit> {
        println("Model: $model")
        return HttpResponse.ok(Unit)
    }
}

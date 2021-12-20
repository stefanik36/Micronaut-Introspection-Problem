package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class DefaultTestControllerTest {
    private val notWellFormedEmailMessage = "model.subModel.email: must be a well-formed email address"

    @Inject
    @field:Client("/")
    private lateinit var httpClient: HttpClient

    @Test
    fun `when sent model, given well formatted email, then status is ok`() {
        //given
        val model = Model(SubModel("test@gmail.com"))
        val request: HttpRequest<Any> = HttpRequest.POST("/test", model)

        //when
        val result = httpClient.toBlocking().exchange(request, Unit::class.java)

        //then
        assertThat(result.status).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `when sent model, given not well formatted email, then status is bad request`() {
        //given
        val model = Model(SubModel("not-valid-email"))
        val request: HttpRequest<Any> = HttpRequest.POST("/test", model)

        //when
        val thrown: HttpClientResponseException = catchThrowableOfType(
            { httpClient.toBlocking().exchange(request, Unit::class.java) },
            HttpClientResponseException::class.java
        )

        //then
        assertThat(thrown.status).isEqualTo(HttpStatus.BAD_REQUEST)
        val body = thrown.response.getBody(String::class.java)
        assertThat(body).isPresent
        assertThat(body.get()).contains(notWellFormedEmailMessage)
    }
}
package ru.romanow.echo.server

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class EchoServerApplicationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun test() {
        val message = "Hello, world"
        mockMvc.post("/") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("message", message)
        }.andExpect {
            status { isOk() }
            content { string(message) }
        }
    }
}

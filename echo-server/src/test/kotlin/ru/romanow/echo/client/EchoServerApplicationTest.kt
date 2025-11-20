package ru.romanow.echo.client

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class EchoServerApplicationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when Send Hello, world then Success`() {
        val message = "Hello, world"
        mockMvc.get("/") {
            queryParam("message", message)
        }.andExpect {
            status { isOk() }
            content { string(message) }
        }
    }
}

package ru.romanow.echo.client

import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.wiremock.spring.EnableWireMock

@ActiveProfiles("test")
@SpringBootTest
@EnableWireMock
@AutoConfigureMockMvc
internal class EchoClientApplicationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when Send Hello, world then Success`() {
        val message = "Hello, world"
        stubFor(get(urlPathEqualTo("/")).withQueryParam("message", equalTo(message)).willReturn(ok(message)))

        mockMvc.get("/?message={message}", message)
            .andExpect {
                status { isOk() }
                content { string(message) }
            }
    }
}

package ru.romanow.echo.server

import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("full")
@SpringBootTest
@Testcontainers
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

        mockMvc.get("/statistics")
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.hello") { value(1) }
                    jsonPath("$.world") { value(1) }
                }
            }
    }

    companion object {
        private const val REDIS_IMAGE = "bitnami/redis:7.2"
        private const val REDIS_PASSWORD = "test"
        private const val EXPOSED_PORT = 6379

        @Container
        var redis: RedisContainer = RedisContainer(REDIS_IMAGE)
            .withEnv("REDIS_PASSWORD", REDIS_PASSWORD)
            .withExposedPorts(EXPOSED_PORT)

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.host") { "localhost" }
            registry.add("spring.data.redis.port") { redis.getMappedPort(EXPOSED_PORT) }
            registry.add("spring.data.redis.password") { REDIS_PASSWORD }
            registry.add("spring.data.redis.client-type") { ClientType.LETTUCE }
        }
    }
}

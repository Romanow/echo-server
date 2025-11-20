package ru.romanow.echo.client.web

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import ru.romanow.echo.client.config.properties.ServiceUrlProperties

@RestController
class EchoController(builder: WebClient.Builder, properties: ServiceUrlProperties) {
    private final val logger = LoggerFactory.getLogger(EchoController::class.java)

    private val webClient = builder.baseUrl(properties.echoServerUrl).build()

    @RequestMapping
    fun echo(@RequestParam message: String): String? {
        logger.info("Get message: '$message'")
        return webClient.get().uri("/?message=$message").retrieve().bodyToMono(String::class.java).block()
    }
}

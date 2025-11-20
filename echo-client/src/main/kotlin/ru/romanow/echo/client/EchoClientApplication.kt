package ru.romanow.echo.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.romanow.echo.client.config.properties.ServiceUrlProperties

@SpringBootApplication
@EnableConfigurationProperties(ServiceUrlProperties::class)
class EchoClientApplication

fun main(args: Array<String>) {
    runApplication<EchoClientApplication>(* args)
}

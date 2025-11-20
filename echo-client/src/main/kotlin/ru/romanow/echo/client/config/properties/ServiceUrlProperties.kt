package ru.romanow.echo.client.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("services")
data class ServiceUrlProperties(
    val echoServerUrl: String
)

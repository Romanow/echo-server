package ru.romanow.echo.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EchoServerApplication

fun main(args: Array<String>) {
    runApplication<EchoServerApplication>(* args)
}

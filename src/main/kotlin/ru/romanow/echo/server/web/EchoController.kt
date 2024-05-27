package ru.romanow.echo.server.web

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EchoController {
    private val logger = LoggerFactory.getLogger(EchoController::class.java)

    @RequestMapping
    fun echo(@RequestParam message: String): String {
        logger.info("Get message: '$message'")
        return message
    }
}

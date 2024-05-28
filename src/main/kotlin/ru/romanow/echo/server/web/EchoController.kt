package ru.romanow.echo.server.web

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.romanow.echo.server.service.StatisticsService

@RestController
class EchoController(
    private val statisticsService: StatisticsService
) {
    private val logger = LoggerFactory.getLogger(EchoController::class.java)

    @RequestMapping
    fun echo(@RequestParam message: String): String {
        logger.info("Get message: '$message'")
        statisticsService.update(message)
        return message
    }

    @GetMapping("/statistics")
    fun statistics(@RequestParam size: Int = 20) = statisticsService.statistics(size)
}

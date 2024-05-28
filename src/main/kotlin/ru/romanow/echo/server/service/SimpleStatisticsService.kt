package ru.romanow.echo.server.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("simple")
class SimpleStatisticsService : StatisticsService {
    override fun update(str: String) {}
    override fun statistics(size: Int) = mapOf<String, Int>()
}

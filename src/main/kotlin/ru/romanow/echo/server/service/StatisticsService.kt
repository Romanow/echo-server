package ru.romanow.echo.server.service

interface StatisticsService {
    fun update(str: String)
    fun statistics(size: Int): Map<String, Int>
}

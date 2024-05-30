package ru.romanow.echo.server.service

import io.micrometer.tracing.annotation.NewSpan
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
@Profile("full")
class FullStatisticsService(redisTemplate: RedisTemplate<String, String>) : StatisticsService {
    private val hashOps: HashOperations<String, String, String> = redisTemplate.opsForHash()

    @NewSpan
    override fun update(str: String) {
        val words = str.split("[^a-zA-Z-']+".toRegex())
        words
            .filter { it.isNotBlank() }
            .forEach { hashOps.increment(COLLECTION, it.lowercase(), 1) }
    }

    @NewSpan
    override fun statistics(size: Int): Map<String, Int> {
        return hashOps.entries(COLLECTION)
            .map { (k, v) -> Pair(k, v.toInt()) }
            .sortedBy { (_, v) -> v }
            .take(size)
            .toMap()
    }

    companion object {
        private const val COLLECTION = "words"
    }
}

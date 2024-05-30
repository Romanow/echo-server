package ru.romanow.echo.server.config

import io.micrometer.common.annotation.NoOpValueResolver
import io.micrometer.common.annotation.ValueExpressionResolver
import io.micrometer.tracing.Tracer
import io.micrometer.tracing.annotation.DefaultNewSpanParser
import io.micrometer.tracing.annotation.ImperativeMethodInvocationProcessor
import io.micrometer.tracing.annotation.MethodInvocationProcessor
import io.micrometer.tracing.annotation.NewSpanParser
import io.micrometer.tracing.annotation.SpanAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.SimpleEvaluationContext

@Configuration
class TracingConfiguration {

    @Bean
    fun newSpanParser() = DefaultNewSpanParser()

    @Bean
    fun valueResolver() = NoOpValueResolver()

    @Bean
    fun valueExpressionResolver() = SpelTagValueExpressionResolver()

    @Bean
    fun methodInvocationProcessor(newSpanParser: NewSpanParser, tracer: Tracer) =
        ImperativeMethodInvocationProcessor(newSpanParser, tracer)

    @Bean
    fun spanAspect(methodInvocationProcessor: MethodInvocationProcessor) = SpanAspect(methodInvocationProcessor)
}

class SpelTagValueExpressionResolver : ValueExpressionResolver {
    override fun resolve(expression: String, parameter: Any?): String? {
        val context = SimpleEvaluationContext.forReadOnlyDataBinding().build()
        return SpelExpressionParser()
            .parseExpression(expression)
            .getValue(context, parameter, String::class.java)
    }
}

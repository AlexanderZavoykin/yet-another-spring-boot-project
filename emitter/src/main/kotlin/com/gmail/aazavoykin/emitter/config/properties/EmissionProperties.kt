package com.gmail.aazavoykin.emitter.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "emission")
data class EmissionProperties(
    val initialDelayMs: Long,
    val fixedDelayMs: Long,
    val duplicateProbability: Double,
)
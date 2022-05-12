package com.gmail.aazavoykin.processor.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "kafka")
data class KafkaProperties(
    val topic: Topic,
    val window: Window,
)

data class Topic(
    val input: String,
    val output: String,
)

data class Window(
    val sizeMs: Long,
)
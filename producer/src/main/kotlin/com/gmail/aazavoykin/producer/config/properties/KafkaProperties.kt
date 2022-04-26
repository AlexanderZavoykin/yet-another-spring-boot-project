package com.gmail.aazavoykin.producer.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "kafka")
data class KafkaProperties(
    val topic: String,
)
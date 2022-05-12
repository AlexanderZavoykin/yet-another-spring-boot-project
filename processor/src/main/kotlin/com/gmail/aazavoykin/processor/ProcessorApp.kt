package com.gmail.aazavoykin.processor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafkaStreams

@EnableKafkaStreams
@ConfigurationPropertiesScan("com.gmail.aazavoykin.processor.config.properties")
@SpringBootApplication
class ProcessorApp

fun main(args: Array<String>) {
    runApplication<ProcessorApp>(*args)
}
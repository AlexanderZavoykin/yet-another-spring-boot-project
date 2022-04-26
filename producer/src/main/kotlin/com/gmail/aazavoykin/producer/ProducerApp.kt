package com.gmail.aazavoykin.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.gmail.aazavoykin.producer.config.properties")
class ProducerApp

fun main(args: Array<String>) {
    runApplication<ProducerApp>(*args)
}

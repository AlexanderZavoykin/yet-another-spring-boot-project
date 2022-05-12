package com.gmail.aazavoykin.emitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ConfigurationPropertiesScan("com.gmail.aazavoykin.emitter.config.properties")
@EnableScheduling
class ProducerApp

fun main(args: Array<String>) {
    runApplication<ProducerApp>(*args)
}

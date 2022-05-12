package com.gmail.aazavoykin.emitter.job

import com.gmail.aazavoykin.common.dto.Message
import com.gmail.aazavoykin.emitter.config.properties.EmissionProperties
import com.gmail.aazavoykin.emitter.config.properties.KafkaProperties
import com.gmail.aazavoykin.emitter.randomString
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class MessageEmissionJob(
    private val kafkaTemplate: KafkaTemplate<String, Message>,
    private val kafkaProperties: KafkaProperties,
    private val emissionProperties: EmissionProperties,
) {

    private var lastId: String = randomString()

    @Scheduled(initialDelayString = "\${emission.initialDelayMs}", fixedDelayString = "\${emission.fixedDelayMs}")
    fun emit() {
        val random = Random.nextDouble(from = 0.0, until = 1.0)

        val id = if (random >= emissionProperties.duplicateProbability) {
            randomString()
        } else {
            lastId
        }

        lastId = id
        val message = Message(id, randomString())
        kafkaTemplate.send(kafkaProperties.topic, randomString(), message)
    }
}
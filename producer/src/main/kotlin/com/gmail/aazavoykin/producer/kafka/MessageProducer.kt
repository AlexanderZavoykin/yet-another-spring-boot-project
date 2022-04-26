package com.gmail.aazavoykin.producer.kafka

import com.gmail.aazavoykin.producer.config.properties.KafkaProperties
import com.gmail.aazavoykin.producer.dto.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class MessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, Message>,
    private val kafkaProperties: KafkaProperties,
) {

    companion object {
        const val KEY = "KEY"
    }

    fun publish(message: Message) {
        kafkaTemplate.send(kafkaProperties.topic, KEY, message)
    }

}
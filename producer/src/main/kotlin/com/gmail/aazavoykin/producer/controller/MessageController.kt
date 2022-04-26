package com.gmail.aazavoykin.producer.controller

import com.gmail.aazavoykin.producer.dto.Message
import com.gmail.aazavoykin.producer.kafka.MessageProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageProducer: MessageProducer,
) {

    @PostMapping("/message")
    fun publish(@RequestBody message: Message): String {
        messageProducer.publish(message)
        return "OK"
    }

}
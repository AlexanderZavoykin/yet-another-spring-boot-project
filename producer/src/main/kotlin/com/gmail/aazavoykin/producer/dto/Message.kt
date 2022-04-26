package com.gmail.aazavoykin.producer.dto

import java.time.OffsetDateTime

data class Message(
    val type: MessageType,
    val payload: String?,
    val timestamp: OffsetDateTime,
)

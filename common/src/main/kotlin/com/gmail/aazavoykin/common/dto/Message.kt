package com.gmail.aazavoykin.common.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("payload")
    val payload: String,
)

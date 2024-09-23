package com.ani.ws.common.data

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ChatResponse(
    val name: String,
    val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: LocalDateTime
)
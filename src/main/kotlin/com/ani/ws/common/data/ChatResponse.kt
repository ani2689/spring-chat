package com.ani.ws.common.data

import java.time.LocalDateTime

data class ChatResponse(
    val name: String,
    val message: String,
    val createdAt: LocalDateTime
)
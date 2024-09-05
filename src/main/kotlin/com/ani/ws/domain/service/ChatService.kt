package com.ani.ws.domain.service

import com.ani.ws.common.data.ChatDto

interface ChatService {
    fun getChat(): List<ChatDto>
}
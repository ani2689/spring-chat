package com.ani.ws.domain.service

import com.ani.ws.common.data.ChatDto

interface ChatService {
    fun getChats(): List<ChatDto>
}
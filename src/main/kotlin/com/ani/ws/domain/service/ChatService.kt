package com.ani.ws.domain.service

import com.ani.ws.common.data.ChatResponse

interface ChatService {
    fun getChats(): List<ChatResponse>
}
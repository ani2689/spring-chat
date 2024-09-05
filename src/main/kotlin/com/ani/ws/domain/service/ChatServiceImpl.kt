package com.ani.ws.domain.service

import com.ani.ws.common.data.ChatResponse
import com.ani.ws.domain.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository
) : ChatService {
    override fun getChats(): List<ChatResponse> =
        chatRepository.findAll()
            .map { chat ->
                ChatResponse(
                    name = chat.name,
                    message = chat.message,
                    createdAt = chat.createdAt
                )
            }
            .sortedBy { it.createdAt }
}
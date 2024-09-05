package com.ani.ws.domain.controller

import com.ani.ws.common.data.ChatRequest
import com.ani.ws.common.data.ChatResponse
import com.ani.ws.domain.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService
) {
    @GetMapping
    fun getChats(): ResponseEntity<List<ChatResponse>> =
        chatService.getChats()
            .let { ResponseEntity.ok(it) }
}
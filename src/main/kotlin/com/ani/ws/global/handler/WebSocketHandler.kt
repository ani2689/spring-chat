package com.ani.ws.global.handler

import com.ani.ws.common.data.ChatRequest
import com.ani.ws.common.data.ChatResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.time.LocalDateTime

@Component
class WebSocketHandler(
    private val objectMapper: ObjectMapper,
    private val sessions: MutableSet<WebSocketSession> = HashSet(),
) : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val chatRequest = objectMapper.readValue(message.payload, ChatRequest::class.java)

        sessions.map { unit -> unit.sendMessage(chatRequest) }
    }

    private fun WebSocketSession.sendMessage(chatRequest: ChatRequest) {
        val chatResponse = chatRequest.run {
            ChatResponse(
                message = message,
                name = name,
                createdAt = LocalDateTime.now()
            )
        }

        TextMessage(objectMapper.writeValueAsString(chatResponse))
            .run(this::sendMessage)
    }
}
package com.ani.ws.global.handler

import com.ani.ws.common.data.ChatDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

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
        val chatDto = objectMapper.readValue(message.payload, ChatDto::class.java)

        sessions.map { unit -> unit.sendMessage(chatDto) }
    }

    private fun WebSocketSession.sendMessage(chatDto: ChatDto) =
        TextMessage(objectMapper.writeValueAsString(chatDto))
            .run(this::sendMessage)
}
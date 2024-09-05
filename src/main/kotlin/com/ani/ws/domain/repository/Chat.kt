package com.ani.ws.domain.repository

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "TEXT")
    val name: String,

    @Column(columnDefinition = "VARCHAR(255)")
    val message: String,

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()
)

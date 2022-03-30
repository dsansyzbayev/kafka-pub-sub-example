package com.example.producer.domain

import java.time.ZonedDateTime

data class Event(
    var id: String,
    var correlationId: String,
    var type: String,
    var createdAt: ZonedDateTime,
    var payload: Map<String, Any>
)

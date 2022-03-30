package com.example.producer.controller

import com.example.producer.domain.Event
import com.example.producer.service.ProducerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
class ProducerController(
    val service: ProducerService
) {

    @GetMapping("/test")
    fun sendRequestToKafka(): ResponseEntity<Any> {

        val event = Event(
            id ="12345",
            correlationId = "1234",
            type = "TEST",
            createdAt = ZonedDateTime.now(),
            payload = mapOf(
                "number" to 1,
                "letter"  to 'c',
                "string" to 'd'
            )
        )

        service.sendMessage(event)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/two")
    fun sendRequestToKafkaTwo(): ResponseEntity<Any> {

        val event = Event(
            id ="12345",
            correlationId = "1234",
            type = "TEST_TWO",
            createdAt = ZonedDateTime.now(),
            payload = mapOf(
                "number" to 1,
                "letter"  to 'c',
                "string" to 'd'
            )
        )

        service.sendMessage(event)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/three")
    fun sendRequestToKafkaThree(): ResponseEntity<Any> {

        val event = Event(
            id ="12345",
            correlationId = "1234",
            type = "TEST_THREE",
            createdAt = ZonedDateTime.now(),
            payload = mapOf(
                "number" to 1,
                "letter"  to 'c',
                "string" to 'd'
            )
        )

        service.sendMessage(event)
        return ResponseEntity.ok().build()
    }
}
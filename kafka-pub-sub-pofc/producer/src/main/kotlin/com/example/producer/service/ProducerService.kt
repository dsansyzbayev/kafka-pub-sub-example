package com.example.producer.service

import com.example.producer.domain.Event
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class ProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    private val logger: Logger = LoggerFactory.getLogger(ProducerService::class.java)


    fun sendMessage(event: Event) {
        val message = objectMapper.writeValueAsString(event)

        logger.info("Message sent -> {}", message);

        kafkaTemplate.send(MessageBuilder.withPayload(message)
            .setHeader(KafkaHeaders.TOPIC, "GENERAL_TOPIC")
            .setHeader("x-application-name", "kafka-pub-sub")
            .build())


    }
}
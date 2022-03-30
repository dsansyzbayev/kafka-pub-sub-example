package com.example.consumer.service

import com.example.consumer.domain.Event
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.protocol.types.Field.Str
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service
import java.util.function.Consumer
import kotlin.reflect.KFunction

@Service
class KafkaConsumer(val objectMapper: ObjectMapper) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    private val handlers: Map<String, (String) -> Unit> = mapOf(
        "TEST" to this::handleTest,
        "TEST_TWO" to this::handleTestTwo
    )

    @KafkaListener(topics = ["GENERAL_TOPIC"])
    fun onReceiving(event: String, @Header(KafkaHeaders.OFFSET) offset: Int,
                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition: Int,
                    @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {
        val jsonNode = objectMapper.readTree(event)
        val type = jsonNode.get("type").asText()
        log.info(type)
        if(handlers.containsKey(type)) {
            handlers[type]?.let { it(event) }
        }
    }

    fun handleTest(event: String) {
        log.info(event)
    }

    fun handleTestTwo(event: String) {
        log.info("test two $event")
    }
}
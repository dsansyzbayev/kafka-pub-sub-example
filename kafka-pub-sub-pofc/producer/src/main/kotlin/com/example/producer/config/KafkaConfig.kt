package com.example.producer.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class KafkaTopicConfig {

    @Bean
    fun newsCreatedTopicConfig(): NewTopic {
        return NewTopic("GENERAL", 3, 1.toShort())
    }
}
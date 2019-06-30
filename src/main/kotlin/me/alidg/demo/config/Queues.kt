package me.alidg.demo.config

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Queues {

    @Bean
    fun firstQ() = Queue("first.queue", true)
}
package me.alidg.demo.web

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/declare")
class SimpleController(private val rabbit: RabbitTemplate) {

    @GetMapping
    fun publishSomething() {
        rabbit.send("first.queue", Message("hello".toByteArray(), MessageProperties()))
    }
}
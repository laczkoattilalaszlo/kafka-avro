package com.example.demo.kafka


import example.output.folder.User
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    @KafkaListener(topics = ["testTopic"])
    fun receiveMessage(data: User) {
        println("RECEIVED MESSAGE FROM KAFKA: $data")
    }

}

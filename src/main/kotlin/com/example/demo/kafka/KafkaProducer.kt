package com.example.demo.kafka

import example.output.folder.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer @Autowired constructor(private val kafkaTemplate: KafkaTemplate<String, User>) {

    fun sendMessage(topic: String, key: String, data: User) {
        kafkaTemplate.send(topic, key, data)    // The key ensures that data with the same key will be stored on the same Partition of a Topic.
        println("THE FOLLOWING MESSAGE WAS SENT:\n" +
                "- topic: $topic\n" +
                "- key: $key\n" +
                "- data: $data\n")
    }

}


package com.example.demo.controller

import com.example.demo.kafka.KafkaProducer
import example.output.folder.User
import io.confluent.kafka.schemaregistry.ParsedSchema
import io.confluent.kafka.schemaregistry.avro.AvroSchema
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController @Autowired constructor(private val kafkaProducer: KafkaProducer, private val schemaRegistryClient: SchemaRegistryClient){

    @GetMapping("/register-schema")
    fun registerSchema() {
        val parsedSchema: ParsedSchema = AvroSchema(User.getClassSchema())
        val schemaId = schemaRegistryClient.register("subject", parsedSchema)
        println("SCHEMA IS REGISTERED WITH ID: $schemaId")
    }

    @PostMapping("/send-message")
    fun sendMessage(@RequestBody data: User) {
        kafkaProducer.sendMessage("testTopic", data.id, data)   // Here we send the User's id as the key.
    }

}
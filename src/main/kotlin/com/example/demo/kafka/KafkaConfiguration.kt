package com.example.demo.kafka

import example.output.folder.User
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate


@Configuration
class KafkaConfiguration {

    @Bean
    fun createTestTopicIfNotExists(): NewTopic {
        println("CREATING/GETTING TOPICS")
        return TopicBuilder.name("testTopic")
            .partitions(1)
            .replicas(1)
            .build()
    }

    @Bean
    fun getSchemaRegistryClient(): SchemaRegistryClient? {
        val schemaRegistryUrl = "http://localhost:8081"
        val cacheCapacity = 10
        return CachedSchemaRegistryClient(schemaRegistryUrl, cacheCapacity)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, User> {
        val bootstrapServer = "localhost:9092"              // Itt hasznalhattunk volna application.yaml property-t is.
        val schemaRegistryUrl = "http://localhost:8081"     // Itt hasznalhattunk volna application.yaml property-t is.

        val configurationProperties = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to KafkaAvroSerializer::class.java,
            "schema.registry.url" to schemaRegistryUrl
        )
        val defaultKafkaProducerFactory = DefaultKafkaProducerFactory<String, User>(configurationProperties)

        return KafkaTemplate(defaultKafkaProducerFactory)
    }

}
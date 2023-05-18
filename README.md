# Kafka with KafkaAvroSerializer

### About the project
This is a demo project for representing a basic Kafka environment with Schema Registry in Kotlin Spring Boot.
* It contains a Kafka Producer and Kafka Consumer
* It contains a Schema Registry.
* The serialization / deserialization process of sending and receiving the messages happens with KafkaAvroSerializer / KafkaAvroDeserializer.
* It also contains a kafka-topics-ui (with kafka-rest-proxy, which is necessary to run kafka-topics-ui), which you can use to check the Kafka topics and messages during you use the application.

### Compile, run and use
1. Hit build button. (There are no additional settings needed to compile the project.)
2. Run docker-compose.yaml file.
3. Start the Spring Boot application.
4. Register a schema to the Schema Registry and send some messages with the help of the endpoints defined in the KafkaController class.
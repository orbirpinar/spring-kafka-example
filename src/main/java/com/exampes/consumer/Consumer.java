package com.exampes.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    Logger logger = LogManager.getLogger(Consumer.class);

    @KafkaListener(topics = "topic",groupId = "first-group")
    public void consume(ConsumerRecord<String,String> record) {
        logger.info("Consumer Record ---> " + record.value());
    }
}

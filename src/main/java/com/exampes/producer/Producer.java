package com.exampes.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {


    private static final  String TOPIC = "topic";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void produce(String message) {
        kafkaTemplate.send(TOPIC,message);
    }
}

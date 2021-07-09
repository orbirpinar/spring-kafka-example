package com.exampes.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {


    @Autowired
    private Producer producer;

    @GetMapping("/produce/{message}")
    public void produce(@PathVariable String message) {
        producer.produce(message);
    }
}

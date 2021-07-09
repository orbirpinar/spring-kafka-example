package com.exampes.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConsumerConfiguration {


    private static final String GROUP_ID = "first-group";

    private static final String BOOTSTRAP_SERVER = "localhost:9092";


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());
        factory.setErrorHandler(this::handleErrors);
        return factory;
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    private void handleErrors(Exception exception, ConsumerRecord<?, ?> data) {
        // HANDLE ERROR
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        /* here retry pol exceptions you wanted to try and what you don't want to retry.*/
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        return retryTemplate;
    }

    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
//        // the boolean value in the map determines whether exception retried or not
//        exceptionMap.put(IllegalArgumentException.class, true);
//        exceptionMap.put(ElementNotInteractableException.class,true);
//        exceptionMap.put(TimeoutException.class, true);
//        exceptionMap.put(StaleElementReferenceException.class,true);

        // Max attempts if fail
        return new SimpleRetryPolicy(3);
    }

}

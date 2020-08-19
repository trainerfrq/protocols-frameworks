package com.training.protocols.amqp;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@EnableScheduling
public class MessageProducer {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public MessageProducer(CamelContext camelContext) {
        this.producerTemplate = camelContext.createProducerTemplate();
    }

    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void sendMessage(){
        String message = "hello! " + new Random().nextInt();
        System.out.println("sending: " + message);
        this.producerTemplate.sendBody("amqp:topic:myTopicMessages", message);
    }

}

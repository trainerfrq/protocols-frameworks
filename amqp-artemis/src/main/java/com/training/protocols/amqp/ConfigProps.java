package com.training.protocols.amqp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProps {

    @Value("${amqp.broker.host}")
    public String amqpBrokerHost;

    @Value("${amqp.broker.port}")
    public int amqpBrokerPort;

    @Value("${amqp.broker.user}")
    public String amqpBrokerUser;

    @Value("${amqp.broker.password}")
    public String amqpBrokerPassword;

    }

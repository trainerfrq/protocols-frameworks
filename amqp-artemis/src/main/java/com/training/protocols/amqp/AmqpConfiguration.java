package com.training.protocols.amqp;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    @Autowired
    private JmsConnectionFactory connectionFactory;

    private final String brokerUrl;
    private final String brokerPassword;
    private final String brokerUsername;
    private final Integer amqpTtl;

    @Autowired
    public AmqpConfiguration(ConfigProps configProps) {
        this.brokerUrl = "amqp://" + configProps.amqpBrokerHost + ":" + configProps.amqpBrokerPort;
        this.brokerUsername = configProps.amqpBrokerUser;
        this.brokerPassword = configProps.amqpBrokerPassword;
        this.amqpTtl = 30;
    }

    @Bean
    @Qualifier("jmsConnectionFactory")
    public JmsConnectionFactory connectionFactory() {
        return new JmsConnectionFactory(brokerUsername, brokerPassword, brokerUrl);
    }

    @Bean
    @Qualifier("amqp")
    public AMQPComponent amqpComponent() {
        JmsConfiguration config = new JmsConfiguration();
        config.setConnectionFactory(connectionFactory);
        config.setCacheLevelName("CACHE_NONE");
        config.setTimeToLive(amqpTtl * 60 * 1000);
        AMQPComponent amqpComponent = new AMQPComponent();
        amqpComponent.setConfiguration(config);
        return amqpComponent;
    }
}

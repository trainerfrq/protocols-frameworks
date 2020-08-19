package com.training.protocols.websocket;

import com.training.parser.UnitSyntaxParser;
import com.training.service.ConverterController;
import com.training.service.UnitConverter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebsocketMessageProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketMessageProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = (String) exchange.getIn().getBody();
        String connectionKey = (String) exchange.getIn().getHeader(WebsocketConstants.CONNECTION_KEY);
        if (message.contains("|")) {
            String[] parts = message.split("\\|");
            String command = parts[0];
            switch (command) {
                case "heartbeat":
                    sendHeartbeatBack(exchange, connectionKey);
                    break;
                case "message":
                    LOGGER.info("received message: " + parts[1]);
                    break;
                case "convert":
                    ConverterController converterController = new ConverterController(
                            new UnitSyntaxParser(),
                            new UnitConverter()
                    );
                    sendConvertedStuff(
                            exchange,
                            connectionKey,
                            converterController.convertFromString(parts[1])
                    );
                    break;
                default:  LOGGER.warn("unknown command!");
            }
        } else {
            LOGGER.error("Message is not well-formatted: " + message);
        }
    }


    private void sendHeartbeatBack(Exchange exchange, String connectionKey) {
        ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate();
        producerTemplate.sendBodyAndHeader(
                "atmosphere-websocket:///hello",
                "heartbeat|" + System.currentTimeMillis(),
                WebsocketConstants.CONNECTION_KEY, connectionKey
        );
    }

    private void sendConvertedStuff(Exchange exchange, String connectionKey, String convertedStuff) {
        ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate();
        producerTemplate.sendBodyAndHeader(
                "atmosphere-websocket:///hello",
                "convertedStuff|" + convertedStuff,
                WebsocketConstants.CONNECTION_KEY, connectionKey
        );
    }

}

package com.training.protocols.websocket;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.apache.camel.component.atmosphere.websocket.WebsocketConstants.*;

@Component
public class WebsocketSessionProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketSessionProcessor.class);

    public void process(Exchange exchange) throws Exception {

        int eventType = (int) exchange.getIn().getHeader(WebsocketConstants.EVENT_TYPE);
        String connectionKey = (String) exchange.getIn().getHeader(WebsocketConstants.CONNECTION_KEY);
        LOGGER.info("Event notification from websocket client: " + eventType);
        switch (eventType){
            case ONOPEN_EVENT_TYPE :
                LOGGER.info("Connection has been established successfully for next connection key: " + connectionKey);
                break;
            case ONCLOSE_EVENT_TYPE :
                LOGGER.info("Connection has been closed successfully for next connection key: " + connectionKey);
                break;
            case ONERROR_EVENT_TYPE :
                LOGGER.info("An error event has been triggered for next connection key: " + connectionKey);
                break;
            default:
                LOGGER.info("Event notification from websocket client is Unknown.");
        }


    }
}

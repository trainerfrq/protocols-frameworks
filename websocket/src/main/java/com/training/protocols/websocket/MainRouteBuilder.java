package com.training.protocols.websocket;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRouteBuilder extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainRouteBuilder.class);

    @Autowired
    private WebsocketSessionProcessor websocketSessionProcessor;

    @Autowired
    private WebsocketMessageProcessor websocketMessageProcessor;


    public void configure() {


        from("atmosphere-websocket:///hello").choice().
                when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONOPEN_EVENT_TYPE)).
                process(websocketSessionProcessor).to("atmosphere-websocket:///hello").
                when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONCLOSE_EVENT_TYPE)).
                process(websocketSessionProcessor).
                when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONERROR_EVENT_TYPE)).
                process(websocketSessionProcessor).
                when(header(WebsocketConstants.ERROR_TYPE).isEqualTo(WebsocketConstants.MESSAGE_NOT_SENT_ERROR_TYPE)).
                process(exchange -> LOGGER.error("message not delivered.")).
                otherwise().process(websocketMessageProcessor);

    }
}

package com.training.protocols.amqp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRouteBuilder extends RouteBuilder {

    public void configure() {

        from("amqp:queue:myHelloMessages").process((e) -> {
            System.out.println("received: " + (String)e.getIn().getBody());
        });
    }
}

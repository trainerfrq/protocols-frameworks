package com.example.training.converterapi.configuration;

import com.example.training.converterapi.controller.ConverterControllerApi;
import com.training.parser.UnitSyntaxParser;
import com.training.service.ConverterController;
import com.training.service.UnitConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConfiguration.class);

    @Bean("unitConverter1")
    @Lazy
    public ConverterController converterController(){
        LOGGER.info("creating bean1 Converter Controller");
        UnitSyntaxParser sp = new UnitSyntaxParser();
        UnitConverter uc = new UnitConverter();
        ConverterController c = new ConverterController(sp, uc);
        LOGGER.info(c.toString());
        return c;
    }

    @Bean("unitConverter2")
    @Lazy
    public ConverterController converterController2(){
        LOGGER.info("creating bean2 Converter Controller");
        UnitSyntaxParser sp = new UnitSyntaxParser();
        UnitConverter uc = new UnitConverter();
        ConverterController c = new ConverterController(sp, uc);
        LOGGER.info(c.toString());
        return c;
    }
}

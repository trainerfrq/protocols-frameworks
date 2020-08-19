package com.example.training.converterapi.controller;

import com.training.parser.UnitSyntaxParser;
import com.training.service.ConverterController;
import com.training.service.UnitConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ConverterControllerApiTest {

    @Autowired
    ConverterControllerApi converterControllerApi;

    @Test
    public void testInitialization() {
        System.out.println(converterControllerApi.getConverterController().toString());
        converterControllerApi
                .convert("20 kilometers to meters");
    }

}
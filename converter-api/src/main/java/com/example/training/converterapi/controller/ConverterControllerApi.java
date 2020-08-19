package com.example.training.converterapi.controller;

import com.training.model.Unit;
import com.training.parser.UnitSyntaxParser;
import com.training.service.ConverterController;
import com.training.service.UnitConverter;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/unit")
public class ConverterControllerApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterControllerApi.class);

    private ConverterController converterController;

    @Autowired
    public ConverterControllerApi(@Qualifier("unitConverter2") ConverterController converterController){
        LOGGER.debug("initialized ConverterControllerApi");
        this.converterController = converterController;
    }

    @RequestMapping(value = "/convert", produces = {"text/plain"}, consumes = {"text/plain"}, method =
            RequestMethod.POST)
    public ResponseEntity<String> convert(@RequestBody String value){
        try {
            return ResponseEntity.ok(converterController.convertFromString(value));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ConverterController getConverterController() {
        return converterController;
    }
}

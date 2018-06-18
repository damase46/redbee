package com.redbee.api.controller;

import com.redbee.api.service.PersistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class TestRestController {

    Logger logger  = Logger.getLogger(TestRestController.class.getName());

    @Autowired
    PersistService persistService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void  getTest(){
        logger.info("Test Work");
    }

    @RequestMapping(value = "/rabbit",method = RequestMethod.GET)
    public void  rabbitTest(){
        logger.info("Rabbit Test Work");
        persistService.delegatePersist("Por ahora prueba");
    }
}

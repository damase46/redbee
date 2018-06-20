package com.redbee.api.controller;

import com.redbee.api.model.Comentario;
import com.redbee.api.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/comment")
public class ComentarioRestController {

    Logger logger  = Logger.getLogger(TestRestController.class.getName());

    @Autowired
    ComentarioService comentarioService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void  update(@RequestBody @Valid Comentario comentario, BindingResult bindingResult){
        logger.info("Call Update Comentario");

        if(bindingResult.hasErrors()){
            return;
        }
        comentarioService.updateComment(comentario);
    }

    @RequestMapping(value = "/updateCustom",method = RequestMethod.POST)
    public void  updateCustom(@RequestBody @Valid Comentario comentario, BindingResult bindingResult){
        logger.info("Call Update Comentario");

        if(bindingResult.hasErrors()){
            return;
        }
        comentarioService.updateCustomComment(comentario);
    }

}

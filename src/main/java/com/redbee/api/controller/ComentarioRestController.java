package com.redbee.api.controller;

import com.redbee.api.model.Comentario;
import com.redbee.api.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
public class ComentarioRestController {

    Logger logger  = Logger.getLogger(TestRestController.class.getName());

    @Autowired
    ComentarioService comentarioService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void  update(@Valid Comentario comentario, BindingResult bindingResult){
        logger.info("Call Update Comentario");

        if(bindingResult.hasErrors()){
            return;
        }
        comentarioService.updateComment(comentario);
    }

    @RequestMapping(value = "/updateMok",method = RequestMethod.GET)
    public void  updateMok(){
        logger.info("Call mok");

        Comentario comment = new Comentario();
        comment.setId(1L);
        comment.setComment("test");
        comment.setName("yo");

        comentarioService.updateComment(comment);
    }
}

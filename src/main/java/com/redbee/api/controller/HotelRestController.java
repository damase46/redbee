package com.redbee.api.controller;

import com.redbee.api.model.Comentario;
import com.redbee.api.model.Hotel;
import com.redbee.api.service.HotelService;
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
@RequestMapping(value = "/hotel")
public class HotelRestController {

    Logger logger  = Logger.getLogger(TestRestController.class.getName());

    @Autowired
    HotelService hotelService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void  update(@RequestBody @Valid Hotel hotel, BindingResult bindingResult){
        logger.info("Call Update Comentario");

        if(bindingResult.hasErrors()){
            return;
        }
        hotelService.updateHotel(hotel);
    }

    @RequestMapping(value = "/updateMok",method = RequestMethod.GET)
    public void  updateMok(){
        logger.info("Call mok");
        Hotel hotel = new Hotel();
        hotelService.updateHotel(hotel);
    }
}

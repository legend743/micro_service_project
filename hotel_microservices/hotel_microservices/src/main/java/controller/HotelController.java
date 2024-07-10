package controller;

import Entity.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.MicroService;

@RestController
@RequestMapping("/hoteldetails")
public class HotelController {

    @Autowired
    private MicroService microserve;

    @PostMapping("/addhotel")
    public ResponseEntity<HotelEntity> addHotels(@RequestBody HotelEntity hotelEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(microserve.Create(hotelEntity));
    }

    @GetMapping("/gethotels/{id}")
    public HotelEntity getHotels(@PathVariable String id){
        return microserve.get(id);
    }
}

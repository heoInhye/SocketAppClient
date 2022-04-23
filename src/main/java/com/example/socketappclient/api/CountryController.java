package com.example.socketappclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@org.springframework.stereotype.Controller
public class CountryController {

    private CountryService service;

    @Autowired
    public void Controller(CountryService service){ this.service=service; }

    @GetMapping("/get")
    public ResponseEntity  getCountries(){
        List<Map> response = service.getCountries();
        return ResponseEntity.ok().body(response);
    }




}

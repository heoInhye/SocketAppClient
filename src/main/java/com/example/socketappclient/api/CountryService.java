package com.example.socketappclient.api;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class CountryService {

    private CountryMapper mapper;

    @Autowired
    public void Service(CountryMapper mapper){ this.mapper=mapper; }

    public List<Map> getCountries(){
        return mapper.getCountries();
    }


}

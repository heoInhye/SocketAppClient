package com.example.socketappclient.api;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface CountryMapper {

    public List<Map> getCountries();

}

package com.wise.weather.weatherwise.controllers;

import com.wise.weather.weatherwise.entity.City;
import com.wise.weather.weatherwise.entity.FamousPlace;
import com.wise.weather.weatherwise.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/saveCity")
    public int saveCity(@RequestBody City city) throws SQLException {
        int id= weatherService.saveCity(city);
        return id;
    }

    @GetMapping("/getCity/{name}")
    public City getCity(@PathVariable("name") String name) throws SQLException {
        City city = weatherService.getCityData(name);
        return city;
    }

    @PostMapping("/updateCity")
    public int updateCity(@RequestBody City city) throws SQLException {
        int id = weatherService.updateCity(city);
        return id;
    }

    @PostMapping("/saveFamousPlace")
    public int saveFamousPlace(@RequestBody FamousPlace famousPlace) throws SQLException {
        return weatherService.saveFamousPlace(famousPlace);
    }

    @GetMapping("/getFamousPlace/{cityName}")
    public List<FamousPlace> getFamousPlace(@PathVariable("cityName") String cityName) throws SQLException {
        return weatherService.getFamousPlaces(cityName);
    }

}

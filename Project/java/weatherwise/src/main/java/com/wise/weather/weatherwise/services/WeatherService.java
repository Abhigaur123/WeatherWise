package com.wise.weather.weatherwise.services;

import com.wise.weather.weatherwise.dao.WeatherDao;
import com.wise.weather.weatherwise.entity.City;
import com.wise.weather.weatherwise.entity.FamousPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class WeatherService {

    @Autowired
    private WeatherDao weatherDao;

    public int saveCity(City city) throws SQLException {

        City alreadyExist = getCityData(city.getName());
        if (alreadyExist==null){
            Random rand = new Random();
            city.setId(rand.nextInt(1000));
            return weatherDao.saveCity(city);
        }else {
            alreadyExist.setWindSpeed(city.getWindSpeed());
            alreadyExist.setHumidity(city.getHumidity());
            alreadyExist.setTemperature(city.getTemperature());
           return updateCity(alreadyExist);
        }

    }

    public City getCityData(String name) throws SQLException {
        return weatherDao.getCityData(name);
    }

    public int updateCity(City city) throws SQLException {
        return weatherDao.updateCity(city);
    }

    public List<FamousPlace> getFamousPlaces(String cityName) throws SQLException {
        City city =  getCityData(cityName);
        if (city == null){
            return new ArrayList<>();
        }
        return weatherDao.getFamousPlaces(city.getId());
    }

    public int saveFamousPlace(FamousPlace famousPlace) throws SQLException {
        Random rand = new Random();
        famousPlace.setId(rand.nextInt(1000));
        return weatherDao.saveFamousPlace(famousPlace);
    }
}

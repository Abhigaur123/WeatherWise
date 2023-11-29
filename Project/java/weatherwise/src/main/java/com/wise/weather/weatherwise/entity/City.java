package com.wise.weather.weatherwise.entity;

import java.util.List;

public class City {

    private int id;
    private String name;

    private String temperature;
    private String humidity;
    private String windSpeed;
    private String uvIndex;
    private String description;
    private List<FamousPlace> famousPlaceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FamousPlace> getFamousPlaceList() {
        return famousPlaceList;
    }

    public void setFamousPlaceList(List<FamousPlace> famousPlaceList) {
        this.famousPlaceList = famousPlaceList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

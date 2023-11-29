package com.wise.weather.weatherwise.dao;

import com.wise.weather.weatherwise.entity.City;
import com.wise.weather.weatherwise.entity.FamousPlace;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class WeatherDao {
    private String url = "jdbc:mysql://localhost:3306/weatherwise";
    private String user = "root";
    private String password = "abhishek@123";

    public int saveCity(City city) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "insert into city(id, name, temperature,humidity,windSpeed,uvIndex,description)" +
                "values("+city.getId()+", '" +city.getName() + "', '" + city.getTemperature() + "', '" + city.getHumidity() + "'" +
                ", '" + city.getWindSpeed() + "', '" + city.getUvIndex() + "', '" + city.getDescription() + "' )";

        System.out.println(">>> Query >> " + query);
       statement.execute(query);
        return city.getId();
    }

    public City getCityData(String name) throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from city where name = '" + name + "'";
        System.out.println(">>> Query >> " + query);
        ResultSet resultSet = statement.executeQuery(query);
        City city = null;
        while (resultSet.next()) {
            city=new City();
            city.setId(resultSet.getInt("id"));
            city.setName(resultSet.getString("name"));
            city.setHumidity(resultSet.getString("humidity"));
            city.setTemperature(resultSet.getString("temperature"));
            city.setDescription(resultSet.getString("description"));
            city.setUvIndex(resultSet.getString("uvIndex"));
            city.setWindSpeed(resultSet.getString("windSpeed"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return city;
    }

    public int updateCity(City city) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        StringBuilder query = new StringBuilder("update city set id = ").append(city.getId());
        if (city.getName()!=null){
            query.append(",name = '").append(city.getName()).append("'");
        }
        if (city.getDescription()!=null){
            query.append(",description = '").append(city.getDescription()).append("'");
        }
        if (city.getHumidity()!=null){
            query.append(",humidity = '").append(city.getHumidity()).append("'");
        }
        if (city.getTemperature()!=null){
            query.append(",temperature = '").append(city.getTemperature()).append("'");
        }
        if (city.getUvIndex()!=null){
            query.append(",uvIndex = '").append(city.getUvIndex()).append("'");
        }
        if (city.getWindSpeed()!=null){
            query.append(",windSpeed = '").append(city.getWindSpeed()).append("'");
        }
        query.append(" where id = ").append(city.getId());
        System.out.println(">>> Query >> " + query);
        return statement.executeUpdate(query.toString());
    }

    private Connection getConnection() {
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, info);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public List<FamousPlace> getFamousPlaces(int cityId) throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from famous_places where city_id =" + cityId;
        System.out.println(">>> Query >> " + query);
        ResultSet resultSet = statement.executeQuery(query);
        List<FamousPlace> famousPlaces = new ArrayList<>();
        while (resultSet.next()) {
            FamousPlace place=new FamousPlace();
            place.setCityId(resultSet.getInt("city_id"));
            place.setName(resultSet.getString("name"));
            place.setUrl(resultSet.getString("url"));
            place.setDescription(resultSet.getString("description"));
            famousPlaces.add(place);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return famousPlaces;
    }

    public int saveFamousPlace(FamousPlace famousPlace) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "insert into famous_places(id, name, description, city_id, url)" +
                "values("+famousPlace.getId()+", '" +famousPlace.getName() + "', '" + famousPlace.getDescription() + "', '"
                +famousPlace.getCityId()+"', '"+ famousPlace.getUrl() + "')";
        System.out.println(">>> Query >> " + query);
        statement.execute(query);
        return famousPlace.getId();
    }
}

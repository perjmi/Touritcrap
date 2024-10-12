package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository //annotation der fortæller spring, at denne klasse har ansvar for adgang til date
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    //    private Connection con;
    final private List<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {

    }


    //CREATE
    public void saveAttraction(TouristAttraction t) throws Exception {

        String sqlString = "INSERT INTO touristattraction(tname, description, pris, city_id) VALUES(?,?,?,?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");


             PreparedStatement statement = con.prepareStatement(sqlString)) {
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrisDollar());
            statement.setInt(4, t.getCityId());
            System.out.println("SQL query: " + sqlString);

            statement.executeUpdate();

        } catch (SQLException err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            System.out.println(dbUrl + " " + username + " " + password);
            err.printStackTrace();
        }
    }


    //READ
    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> attractions = new ArrayList<>();
        String sqlString = "SELECT * FROM touristattraction";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String tname = resultSet.getString("tname");
                String description = resultSet.getString("description");
                double pris = resultSet.getDouble("pris");
                int cityid = resultSet.getInt("city_id");
                attractions.add(new TouristAttraction(tname, description, pris, cityid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attractions;
    }


    public TouristAttraction getAttractionByName(String name) {
        String sqlString = "SELECT t.tname, t.description, t.pris, c.city_id, c.name FROM touristattraction t JOIN city c ON t.city_id = c.city_id WHERE t.tname = ?";
        TouristAttraction touristAttraction = null;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             PreparedStatement statement = con.prepareStatement(sqlString)) {

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            // Hvis der findes en attraktion med det navn, opret et Java TouristAttraction-objekt
            if (resultSet.next()) {
                String attractionName = resultSet.getString("tname");
                String description = resultSet.getString("description");
                double pris = resultSet.getDouble("pris");
                int cityId = resultSet.getInt("city_id");

                touristAttraction = new TouristAttraction(attractionName, description, pris, cityId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return touristAttraction;
    }



    public void deleteAttraction(String name) {
        String sqlString = "DELETE FROM touristattraction WHERE tname = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             PreparedStatement statement = con.prepareStatement(sqlString)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }


    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        String sqlString = "SELECT * FROM city";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");

             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int cityid = resultSet.getInt("city_id");
                cities.add(new City(name, cityid));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    public List<Tag> getTags(TouristAttraction t) {
        return t.getTags();
    }


    public void updateAttraction(TouristAttraction updatedAttraction) {
        String sqlString = "UPDATE touristattraction SET description = ?, pris = ?, city_id = ? WHERE tname = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             PreparedStatement statement = con.prepareStatement(sqlString)) {

            // Sæt parametrene for opdateringen
            statement.setString(1, updatedAttraction.getDescription());
            statement.setDouble(2, updatedAttraction.getPrisDollar());
            statement.setInt(3, updatedAttraction.getCityId());
            statement.setString(4, updatedAttraction.getName());

            // Udfør opdateringen
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
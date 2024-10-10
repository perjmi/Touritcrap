package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import jakarta.annotation.PostConstruct;
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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
            System.out.println("Forbindelse til databasen er oprettet med succes!");

            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrisDollar());
            statement.setInt(4, t.getCity().getId());
            System.out.println("SQL query: " + sqlString);

            statement.executeUpdate();

        } catch (SQLException err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            System.out.println(this.dbUrl + " " + this.username + " " + this.password);
            err.printStackTrace();
        }
    }


    //READ
    public List<TouristAttraction> getAllAttractions() {
        return touristAttractions;
//        List<TouristAttraction> attractions = new ArrayList<>();
//        String sqlString = "SELECT * FROM touristattraction";
//        try (
//                Connection con = DriverManager.getConnection(this.dbUrl, this.username, this.password);
//                Statement statement = con.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(sqlString);
//
//            while (resultSet.next()) {
//                String tname = resultSet.getString("tname");
//                String description = resultSet.getString("description");
//                double pris = resultSet.getDouble("pris");
//                int cityid = resultSet.getInt("city_id");
//                attractions.add(new TouristAttraction(tname, description, pris, cityid));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return attractions;
    }


    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public void deleteAttraction(String name) {
        Iterator<TouristAttraction> iterator = touristAttractions.iterator(); //opretter iterator på touristattraction samling
        while (iterator.hasNext()) { //så længe der er flere elementer i samling
            TouristAttraction t = iterator.next(); //henter det næste element fra samling
            if (t.getName().equals(name)) {
                iterator.remove(); // Brug iterator til sikker fjernelse
                break;
            }
        }

//        try (Connection con = DriverManager.getConnection(this.dbUrl, this.username, this.password);) {
//            String sqlString = "DELETE FROM touristattraction WHERE tname = ?";
//            PreparedStatement statement = con.prepareStatement(sqlString);
//            statement.setString(1, name);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            // better error handling is required
//            e.printStackTrace();
//        }
    }


    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        String sqlString = "SELECT * FROM city";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
            System.out.println("Forbindelse til databasen er oprettet med succes!");

            Statement statement = con.createStatement();
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
        TouristAttraction existingAttraction = getAttractionByName(updatedAttraction.getName());
        if (existingAttraction != null) {
            existingAttraction.setDescription(updatedAttraction.getDescription());
            existingAttraction.setCity(updatedAttraction.getCity());
            existingAttraction.setTags(updatedAttraction.getTags());
        }

//        try (Connection con = DriverManager.getConnection(this.dbUrl, this.username, this.password);) {
//             String sqlString = "UPDATE touristattraction SET SAL = SAL + ? WHERE EMPNO = ?";
//             PreparedStatement statement = con.prepareStatement(sqlString);
//             statement.setInt(1, amount);
//
//             statement.executeUpdate();
//        } catch (SQLException e) {
//        // better error handling is required
//        e.printStackTrace();
//
//    }

    }
}
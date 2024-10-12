package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;

import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
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
//    public void saveAttraction(TouristAttraction t)  {
//
//        String sqlString = "INSERT INTO touristattraction(name, description, prisDollar, cityId) VALUES(?,?,?,?)";
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie")){
//
//
//             PreparedStatement statement = con.prepareStatement(sqlString);
//            statement.setString(1, t.getName());
//            statement.setString(2, t.getDescription());
//            statement.setDouble(3, t.getPrisDollar());
//            statement.setInt(4, t.getCityId());
//            System.out.println("SQL query: " + sqlString);
//
//            statement.executeUpdate();
//
//        } catch (SQLException err) {
//            System.out.println("An error has occurred.");
//            System.out.println("See full details below.");
//            System.out.println(dbUrl + " " + username + " " + password);
//            err.printStackTrace();
//        }
//    }
    public List<TouristAttractionTagDTO> getAllDTOAttractions() {
        List<TouristAttractionTagDTO> attractions = new ArrayList<>();
        String sqlString = "SELECT name, tourist_id, description, prisDollar, cityId FROM touristattraction";
        String sqlString2 = "SELECT tag_id FROM touristattraction_tag WHERE tourist_id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie")) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int tourist_id = resultSet.getInt("tourist_id");
                String description = resultSet.getString("description");
                double prisDollar = resultSet.getDouble("prisDollar");
                int cityId = resultSet.getInt("cityId");

                PreparedStatement statement2 = con.prepareStatement(sqlString2);
                statement2.setInt(1, tourist_id);
                ResultSet resultsetTags = statement2.executeQuery();
                List<Integer> attractionTags = new ArrayList<>();


                while (resultsetTags.next()) {
                    attractionTags.add(resultsetTags.getInt("tag_id"));
                }
                TouristAttractionTagDTO dto = new TouristAttractionTagDTO(name, tourist_id, description, prisDollar, cityId, attractionTags);
                attractions.add(dto);
            }

        } catch (SQLException err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
        return attractions;
    }

    public void saveDTOAttraction(TouristAttractionTagDTO t) {

        String sqlString = "INSERT INTO touristattraction(name, description, prisDollar, cityId) VALUES(?,?,?,?)";
        String sqlTags = "INSERT INTO touristattraction_tag(tourist_id, tag_id) VALUES(?,?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie")) {


            PreparedStatement statement = con.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setDouble(3, t.getPrisDollar());
            statement.setInt(4, t.getCityId());
            System.out.println("SQL query: " + sqlString);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int tourist_id = rs.getInt(1);


                PreparedStatement statementTags = con.prepareStatement(sqlTags);
                for (int tagId : t.getTagIds()) {
                    statementTags.setInt(1, tourist_id);
                    statementTags.setInt(2, tagId);
                    statementTags.executeUpdate();
                }
            }

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
                String tname = resultSet.getString("name");
                String description = resultSet.getString("description");
                double pris = resultSet.getDouble("prisDollar");
                int cityid = resultSet.getInt("cityId");
                attractions.add(new TouristAttraction(tname, description, pris, cityid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attractions;
    }


    public TouristAttraction getAttractionByName(String name) {
        String sqlString = "SELECT t.name, t.description, t.prisDollar, c.cityId, c.name FROM touristattraction t JOIN city c ON t.cityId = c.cityId WHERE t.name = ?";
        TouristAttraction touristAttraction = null;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             PreparedStatement statement = con.prepareStatement(sqlString)) {

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            // Hvis der findes en attraktion med det navn, opret et Java TouristAttraction-objekt
            if (resultSet.next()) {
                String attractionName = resultSet.getString("name");
                String description = resultSet.getString("description");
                double pris = resultSet.getDouble("prisDollar");
                int cityId = resultSet.getInt("cityId");

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
                int cityid = resultSet.getInt("cityId");
                cities.add(new City(name, cityid));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public List<Tag> getAvaliableTags() {
        List<Tag> avaliableTags = new ArrayList<>();
        String sqlString = "SELECT * FROM tag";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");

             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String name = resultSet.getString("tag_name");
                int tagId = resultSet.getInt("tag_id");
                avaliableTags.add(new Tag(name, tagId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avaliableTags;
    }


    public List<Tag> getTags(TouristAttraction t) {
        List<Tag> attractionTags = new ArrayList<>();
        String sqlString = "SELECT touristattraction_tag.tag_id, tag.tag_name FROM touristattraction_tag INNER JOIN tags ON touristattraction_tag.tag_id = tags.tag_id AND touristattraction_tag.tourist_id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/touristattraction", "root", "amalie");
             PreparedStatement statement = con.prepareStatement(sqlString)) {
            statement.setInt(1, t.getTourist_id());
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String tagName = resultSet.getString("tag_name");
                int tagId = resultSet.getInt("tag_id");
                attractionTags.add(new Tag(tagName, tagId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractionTags;
    }


    public void updateAttraction(TouristAttraction updatedAttraction) {
        String sqlString = "UPDATE touristattraction SET description = ?, pris = ?, cityId = ? WHERE tname = ?";

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
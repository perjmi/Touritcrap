package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;

import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

@Repository //annotation der fortæller spring, at denne klasse har ansvar for adgang til date
public class TouristRepository {
    private static final Logger logger = LoggerFactory.getLogger(TouristRepository.class);
    private final String dbUrl;
    private final String username;
    private final String password;

    // Konstruktør med @Value-injektion
    public TouristRepository(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }


    public String getDbUrl() {
        return dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<TouristAttractionTagDTO> getAllDTOAttractions() {
        List<TouristAttractionTagDTO> attractions = new ArrayList<>();
        String sqlString = "SELECT name, tourist_id, description, prisDollar, cityId FROM touristattraction";
        String sqlString2 = "SELECT tag_id FROM touristattraction_tag WHERE tourist_id = ?";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
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

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
        }
        return attractions;
    }

    public void saveDTOAttraction(TouristAttractionTagDTO t) {

        String sqlString = "INSERT INTO touristattraction(name, description, prisDollar, cityId) VALUES(?,?,?,?)";
        String sqlTags = "INSERT INTO touristattraction_tag(tourist_id, tag_id) VALUES(?,?)";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {

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

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
            System.out.println(dbUrl + " " + username + " " + password);
        }

    }

    public TouristAttractionTagDTO getDTOAttractionById(int id) {
        String sqlString = "SELECT name, description, prisDollar, tourist_id, cityId FROM touristattraction WHERE tourist_id = ?";
        String sqlString2 = "SELECT tag_id FROM touristattraction_tag WHERE tourist_id = ?";
        TouristAttractionTagDTO dto = new TouristAttractionTagDTO();
        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int tourist_id = resultSet.getInt("tourist_id");
                String description = resultSet.getString("description");
                double prisDollar = resultSet.getDouble("prisDollar");
                int cityId = resultSet.getInt("cityId");

                PreparedStatement statement2 = con.prepareStatement(sqlString2);
                statement2.setInt(1, id);
                ResultSet resultsetTags = statement2.executeQuery();

                List<Integer> attractionTags = new ArrayList<>();
                while (resultsetTags.next()) {
                    attractionTags.add(resultsetTags.getInt("tag_id"));
                }
                dto = new TouristAttractionTagDTO(name, tourist_id, description, prisDollar, cityId, attractionTags);
            }

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
        }
        return dto;
    }


    //READ
    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> attractions = new ArrayList<>();
        String sqlString = "SELECT * FROM touristattraction";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password);
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
            logger.error("SQL exception occurred", e);
        }

        return attractions;
    }


    public TouristAttraction getAttractionById(int id) {
        String sqlString = "SELECT t.name, t.description, t.prisDollar, t.tourist_id, t.cityId FROM touristattraction t  WHERE t.tourist_id = ?";
        TouristAttraction touristAttraction = null;

        try (Connection con = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement statement = con.prepareStatement(sqlString)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            // Hvis der findes en attraktion med det navn, opret et Java TouristAttraction-objekt
            if (resultSet.next()) {
                String attractionName = resultSet.getString("name");
                String description = resultSet.getString("description");
                double pris = resultSet.getDouble("prisDollar");
                int touristId = resultSet.getInt("tourist_id");
                int cityId = resultSet.getInt("cityId");

                touristAttraction = new TouristAttraction(attractionName, description, pris, touristId, cityId);
            }

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
        }

        return touristAttraction;
    }


    public void deleteDTOAttraction(int id) {
        String sqlStringTag = "DELETE FROM touristattraction_tag WHERE tourist_id = ?";
        String sqlString = "DELETE FROM touristattraction WHERE tourist_id = ?";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement statement2 = con.prepareStatement(sqlStringTag)) {
            statement2.setInt(1, id);
            statement2.executeUpdate();

            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setInt(1, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
        }
    }


    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        String sqlString = "SELECT * FROM city";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password);

             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlString);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int cityid = resultSet.getInt("cityId");
                cities.add(new City(name, cityid));
            }

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
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
            logger.error("SQL exception occurred", e);
        }
        return avaliableTags;
    }

    public List<Tag> getTags(TouristAttraction t) {
        List<Tag> attractionTags = new ArrayList<>();
        String sqlString = "SELECT touristattraction_tag.tag_id, tag.tag_name FROM touristattraction_tag, tag WHERE touristattraction_tag.tag_id = tag.tag_id AND touristattraction_tag.tourist_id = ?";
        try (Connection con = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement statement = con.prepareStatement(sqlString)) {
            statement.setInt(1, t.getTourist_id());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tagName = resultSet.getString("tag_name");
                int tagId = resultSet.getInt("tag_id");
                attractionTags.add(new Tag(tagName, tagId));
            }

        } catch (SQLException e) {
            logger.error("SQL exception occurred", e);
        }
        return attractionTags;
    }


    public void updateAttraction(TouristAttractionTagDTO dto) {
        deleteDTOAttraction(dto.getTourist_id());
        saveDTOAttraction(dto);
    }

}
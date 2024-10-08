package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository //annotation der fortæller spring, at denne klasse har ansvar for adgang til date
public class TouristRepository {
    private Connection con;

    public TouristRepository() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_dept", "amal", "amal");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CREATE
    public void saveAttraction(TouristAttraction t) throws Exception { //paramettr inde i parantesen, parametreliste
        try (Statement stmt = con.createStatement()) {
            int rowcount = stmt.executeUpdate("INSERT INTO touristattraction(tname, description, pris, city_id) VALUES('Tivoli','A amusement park', 150,2)");
            System.out.println();
            System.out.printf("Success - %d - rows affected.\n", rowcount);
        } catch (Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
    }

    //READ
    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> attractions = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM touristattraction";
            // Opret Statement for at udføre forespørgslen
            Statement stmt = con.createStatement();
            // Udfør forespørgslen og få resultatet
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                TouristAttraction t = new TouristAttraction(rs.getString("tname"), rs.getString("description"),getCityNameById(rs.getInt("city_id")), rs.getDouble("pris"));
                attractions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public String getCityNameById(int cityId) {
        String cityName = null;
        try  {
            String SQL = "SELECT city_name FROM city WHERE city_id = ?";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, cityId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cityName = rs.getString("city_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityName;
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
    }


    public List<String> getCities() {
        List<String> citites = new ArrayList<>();
        Collections.addAll(citites, "Roskilde", "Copenhagen", "Herning", "Holstebro", "Aarhus");
        return citites;
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
    }
}
//package com.amalie.thymeleaf.touristguide.repository;
//
//import com.amalie.thymeleaf.touristguide.model.City;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToCityConverter implements Converter<String, City> {
//    @Override
//    public City convert(String source) {
//        try {
//            int cityId = Integer.parseInt(source);  // Konverter String til int
//            // Returnér City-objekt baseret på cityId. Her kan du slå byer op fra din database, hvis nødvendigt.
//            return new City(cityId); // Husk at tilføje en passende City-konstruktør, der accepterer ID.
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid City ID: " + source, e);
//        }
//    }
//}

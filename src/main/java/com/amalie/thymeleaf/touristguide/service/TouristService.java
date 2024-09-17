package com.amalie.thymeleaf.touristguide.service;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristService {
    TouristRepository touristRepository;

    public TouristService() {
        touristRepository = new TouristRepository();
    }

    //CREATE
    public void saveAttraction(TouristAttraction t) {
        touristRepository.saveAttraction(t);
    }

    //READ
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }


//    public void editAttraction(String name, String updatedName) {
//        touristRepository.editAttraction(name, updatedName);
//    }

    public void deleteAttraction(String name) {
        touristRepository.deleteAttraction(name);
    }
    public List<String> getCities() {
       return touristRepository.getCities();
    }

    public List<String> getTags() {
       return touristRepository.getTags();
    }

}

package com.amalie.thymeleaf.touristguide.service;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristService {
    TouristRepository touristRepository;

    public TouristService() {
        touristRepository = new TouristRepository();
    }

    //CREATE
    public void saveAttraction(TouristAttraction t) throws Exception {
        touristRepository.saveAttraction(t);
    }

    //READ
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public void deleteAttraction(String name) {
        touristRepository.deleteAttraction(name);
    }

    public List<String> getCities() {
        return touristRepository.getCities();
    }
    
    public List<Tag> getTags(TouristAttraction t) {
        return touristRepository.getTags(t);
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        touristRepository.updateAttraction(updatedAttraction);
//        TouristAttraction existingAttraction = touristRepository.getAttractionByName(updatedAttraction.getName());
//        if (existingAttraction != null) {
//            existingAttraction.setDescription(updatedAttraction.getDescription());
//            existingAttraction.setCity(updatedAttraction.getCity());
//            existingAttraction.setTags(updatedAttraction.getTags());
//        }
    }

}

package com.amalie.thymeleaf.touristguide.service;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import com.amalie.thymeleaf.touristguide.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class TouristService {
    @Autowired
    TouristRepository touristRepository;

    public TouristService() {
        touristRepository = new TouristRepository();
    }

    public List<TouristAttractionTagDTO> getAllDTOAttractions() {
        return touristRepository.getAllDTOAttractions();
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

    public List<City> getCities() {
        return touristRepository.getCities();
    }
    public List<Tag> getAvaliableTags() {
        return touristRepository.getAvaliableTags();
    }
    
    public List<Tag> getTags(TouristAttraction t) {
        return touristRepository.getTags(t);
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        touristRepository.updateAttraction(updatedAttraction);
    }
    public void saveDTOAttraction(TouristAttractionTagDTO t) {
        touristRepository.saveDTOAttraction(t);
    }

}

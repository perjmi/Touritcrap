package com.amalie.thymeleaf.touristguide.service;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import com.amalie.thymeleaf.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository=touristRepository;
    }

    public List<TouristAttractionTagDTO> getAllDTOAttractions() {
        return touristRepository.getAllDTOAttractions();
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }


    public void deleteDTOAttraction(int id) {
        touristRepository.deleteDTOAttraction(id);
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

    public void updateAttraction(TouristAttractionTagDTO dto) {
        touristRepository.updateAttraction(dto);
    }
    public void saveDTOAttraction(TouristAttractionTagDTO t) {
        touristRepository.saveDTOAttraction(t);
    }
    public TouristAttraction getAttractionById(int id) {
        return touristRepository.getAttractionById(id);
    }
    public TouristAttractionTagDTO getDTOAttractionById(int id) {
        return touristRepository.getDTOAttractionById(id);
    }

}

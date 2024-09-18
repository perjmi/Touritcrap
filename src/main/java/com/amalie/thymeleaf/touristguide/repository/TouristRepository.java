package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TouristRepository {
    final private List<TouristAttraction> touristAttractions;

    public TouristRepository() {
        touristAttractions = new ArrayList<>(
        );
        TouristAttraction t1 = new TouristAttraction("Tivoli", "A amusement park", "Copenhagen");
        t1.setTags(Arrays.asList(Tag.FORLYSTELSE, Tag.BALLON));
        TouristAttraction t2 = new TouristAttraction("Zoo", "A wildlife park, home to a wide variety of animals from around the world.", "Copenhagen");
        t2.setTags(Arrays.asList(Tag.NATUR, Tag.NATUR));
        Collections.addAll(touristAttractions, t1, t2);
    }

    //CREATE
    public void saveAttraction(TouristAttraction t) {
        touristAttractions.add(t);
    }

    //READ
    public List<TouristAttraction> getAllAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }


    //DELETE
    public void deleteAttraction(String name) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equals(name)) {
                touristAttractions.remove(t);
            }
        }
    }

    public List<String> getCities() {
        List<String> citites = new ArrayList<>();
        Collections.addAll(citites, "Roskilde", "Copenhagen", "Herning", "Holstebro", "Aarhus");
        return citites;
    }

    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        for (TouristAttraction t : touristAttractions) {
            for (Tag tag : t.getTags()) {
                tags.add(tag.getDisplayName());
            }
        }
        return tags;
    }

    public void updateAttraction(TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equals(updatedAttraction.getName())) {
                attraction.setDescription(updatedAttraction.getDescription());
                attraction.setCity(updatedAttraction.getCity());
                attraction.setTags(updatedAttraction.getTags());
            }
        }
    }
}

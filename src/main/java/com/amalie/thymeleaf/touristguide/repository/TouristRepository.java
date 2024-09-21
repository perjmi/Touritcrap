package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TouristRepository {
    final private List<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        TouristAttraction t1 = new TouristAttraction("Tivoli", "A amusement park", "Copenhagen", 100);
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

    //    public void deleteAttraction(String name) {
//        for (TouristAttraction t : touristAttractions) {
//            if (t.getName().equals(name)) {
//                touristAttractions.remove(t);
//            }
//        }
//    }
    //DELETE
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
        for (TouristAttraction attraction : touristAttractions) {
            if (attraction.getName().equals(updatedAttraction.getName())) {
                attraction.setDescription(updatedAttraction.getDescription());
                attraction.setCity(updatedAttraction.getCity());
                attraction.setTags(updatedAttraction.getTags());
            }
        }
    }
}

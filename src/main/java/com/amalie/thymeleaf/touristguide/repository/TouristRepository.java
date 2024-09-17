package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> touristAttractions;

    public TouristRepository() {
        touristAttractions = new ArrayList<>(
        );
        TouristAttraction t1 = new TouristAttraction("Tivoli", "A playfull wonderland", "Copenhagen");
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

    //UPDATE
    public void updateAttraction(int index, TouristAttraction t) {
        touristAttractions.set(index, t);
    }

    public void editAttraction(String name, String updatedName) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equals(name)) {
                t.setName(updatedName);
            }
        }
    }

    //DELETE
    public void deleteAttraction(String name) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equals(name)) {
                touristAttractions.remove(t);
            }
        }
    }
}

package com.amalie.thymeleaf.touristguide.repository;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.model.TouristAttractionTagDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TouristRepositoryTest {

    @Test
    void saveAttractionSize() throws Exception {
        //Arrange-setup our test objects*
        TouristRepository touristRepository = new TouristRepository();
        TouristAttractionTagDTO touristAttraction = new TouristAttraction("Attraction Name", "Attraction Description", 3);
        touristRepository.saveDTOAttraction(touristAttraction);
        //Act- do the actual calc or method*
        int expectedSize = 3;
        int actualSize = touristRepository.getAllDTOAttractions().size();

        //Assert-check if actual val is equal to expected val*
        Assertions.assertEquals(expectedSize, actualSize);

    }

    @Test
    void saveAttractionTags() {
        //Arrange-setup our test objects*
        TouristRepository touristRepository = new TouristRepository();
        TouristAttraction touristAttraction = new TouristAttraction("Amalie", "Very sweet", "Copenhagen");
        touristAttraction.setTags(Arrays.asList(Tag.NATUR, Tag.BALLON, Tag.FORLYSTELSE));
        //Act- do the actual calc or method*
        int expectedSize = 3;
        int actualSize = touristAttraction.getTags().size();
        //Assert-check if actual val is equal to expected val*
        Assertions.assertEquals(expectedSize, actualSize);

    }


    @Test
    void updateAttraction() {
        TouristRepository touristRepository = new TouristRepository();
        TouristAttraction attraction = touristRepository.getAttractionByName("Tivoli");
        TouristAttraction updatedAttraction = new TouristAttraction("Tivoli", "Updated Amalie", "Copenhagen");
        touristRepository.updateAttraction(updatedAttraction);

        Assertions.assertEquals(updatedAttraction.getName(), attraction.getName());
        Assertions.assertEquals(updatedAttraction.getDescription(), attraction.getDescription());
        Assertions.assertEquals(updatedAttraction.getCity(), attraction.getCity());
    }

    @Test
    void onlyOneNameCanBeAddedAttraction() throws Exception {
        TouristRepository touristRepository = new TouristRepository();

        TouristAttraction touristAttraction = new TouristAttraction("Tivoli", "doomed too fail", "fail");
        touristRepository.saveAttraction(touristAttraction);

        Exception exception = assertThrows(Exception.class, () -> {
            touristRepository.saveAttraction(touristAttraction);
        });

        assertEquals("Name already added", exception.getMessage());

    }
}
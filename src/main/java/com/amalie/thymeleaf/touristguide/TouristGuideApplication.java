package com.amalie.thymeleaf.touristguide;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TouristGuideApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TouristGuideApplication.class, args);

    }

}

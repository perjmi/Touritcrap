package com.amalie.thymeleaf.touristguide.controller;

import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.repository.CurrencyService;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class) //vi vil kun teste en controller
class TouristControllerTest {

    private TouristAttraction touristAttraction = new TouristAttraction();

    @Autowired //spring instantierer selv objekt
    private MockMvc mockMvc;

    @MockBean //gør os i stand til at ui unittest controller
    private TouristService touristService;

    @MockBean //mock af interne objekter
    private CurrencyService currencyConverterService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    void showTags() throws Exception {
        String attractionName = "Tivoli";
        mockMvc.perform(get("/attractions/{name}/tags", attractionName))
                .andExpect(status().isOk())
                .andExpect(view().name("showTags"))
                .andExpect(content().string(containsString("BALLON")));
    }

    @Test
    void createTouristAttractionForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("attraction"))
                .andExpect(view().name("addAttraction"));
    }

    @Test
    void saveTouristAttraction() throws Exception {
        mockMvc.perform(post("/save").sessionAttr("touristAttraction", this.touristAttraction))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));
    }


}
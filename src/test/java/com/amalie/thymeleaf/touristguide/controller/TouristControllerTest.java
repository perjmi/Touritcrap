package com.amalie.thymeleaf.touristguide.controller;

import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.repository.CurrencyService;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TouristController.class) //vi vil kun teste en controller
class TouristControllerTest {

    private TouristAttraction touristAttraction = new TouristAttraction();

    @Autowired //spring instantierer selv objekt
    private MockMvc mockMvc;

    @MockBean //gør os i stand til at ui test controller
    private TouristService touristService;

    @MockBean
    private CurrencyService currencyConverterService;

    @Test
    void getAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }
    @Test
    void saveTouristAttraction() throws Exception {
        mockMvc.perform(post("/save").sessionAttr("touristAttraction", this.touristAttraction))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));
    }






}
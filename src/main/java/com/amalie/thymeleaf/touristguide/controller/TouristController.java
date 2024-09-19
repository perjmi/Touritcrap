package com.amalie.thymeleaf.touristguide.controller;

import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private final TouristService touristService;

    public TouristController() {
        touristService = new TouristService();
    }

    @GetMapping("/attractions")
    public String getAttractions(Model model) {
        List<TouristAttraction> touristAttractions = touristService.getAllAttractions();
        model.addAttribute("attractions", touristAttractions);
        return "attractionList";
    }

    @GetMapping("/attractions/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction t = touristService.getAttractionByName(name);
        model.addAttribute("attraction", t);
        model.addAttribute("tags", touristService.getTags(t));
        return "tags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction t = new TouristAttraction();
        model.addAttribute("attraction", t);
        model.addAttribute("availableTags", Arrays.asList(Tag.values()));
        model.addAttribute("name", t.getName());
        model.addAttribute("description", t.getDescription());
        model.addAttribute("city", touristService.getCities());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        touristService.saveAttraction(touristAttraction);
        model.addAttribute("attraction", touristAttraction);
        return "redirect:/attractions";

    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction t = touristService.getAttractionByName(name);
        model.addAttribute("attraction", t);
        model.addAttribute("city", touristService.getCities());
        model.addAttribute("description", t.getDescription());
        model.addAttribute("availableTags", Tag.values());
        return "editAttraction";
    }

    @PostMapping("/update")
    public String editAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        touristService.deleteAttraction(touristAttraction.getName());
        model.addAttribute("attraction", touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/info")
    public String getInfo() {
        return "info";
    }
}

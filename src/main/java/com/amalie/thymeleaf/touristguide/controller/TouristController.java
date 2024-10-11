package com.amalie.thymeleaf.touristguide.controller;

import com.amalie.thymeleaf.touristguide.model.City;
import com.amalie.thymeleaf.touristguide.model.Tag;
import com.amalie.thymeleaf.touristguide.model.TouristAttraction;
import com.amalie.thymeleaf.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class TouristController {
    private final TouristService touristService;

    public TouristController() {
        touristService = new TouristService(); //vi inistaniserer
    }

    @GetMapping("/attractions")
    public String getAttractions(@RequestParam(defaultValue = "EUR") String valuta, Model model) { // model bruger spring til at kommunikere med th
        List<TouristAttraction> touristAttractions = touristService.getAllAttractions();
        model.addAttribute("attractions", touristAttractions); //sender med som argument
        model.addAttribute("valuta", valuta);
        return "attractionList"; // alt ovenstående sendes videre til vores view
    }

    @PostMapping("/attractions")
    public String getValuta(@RequestParam String valuta, RedirectAttributes redirectAttributes) { //request fordi vi skal sende en request med som typisk ligger i html
        if (!valuta.equals("EUR")) {
            valuta = "DKK";
        }
        redirectAttributes.addAttribute("valuta", valuta);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) { //pathvariable fordi den kommer fra url path
        TouristAttraction t = touristService.getAttractionByName(name);
        model.addAttribute("attraction", t);
        model.addAttribute("tags", touristService.getTags(t));
        return "showTags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction t = new TouristAttraction();
        model.addAttribute("attraction", t);
        model.addAttribute("availableTags", Arrays.asList(Tag.values()));
        model.addAttribute("name", t.getName());
        model.addAttribute("description", t.getDescription());
        model.addAttribute("cities", touristService.getCities());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) throws Exception{
        System.out.println(touristAttraction);
        System.out.println(touristAttraction.getCity());

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
        return "redirect:/attractions"; //redirect: sig til klienten bed om denne side
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.deleteAttraction(touristAttraction.getName());
        return "redirect:/attractions";
    }

}

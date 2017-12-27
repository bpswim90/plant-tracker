package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.PlantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="plant/type")
public class PlantTypeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        PlantType aloe = new PlantType("Aloe",21);

        model.addAttribute("plantType",aloe);
        model.addAttribute("title","Plant Profile: " + aloe.getName());

        return "plantType/index";
    }
}

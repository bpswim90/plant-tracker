package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.PlantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="plant/type")
public class PlantTypeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        PlantType aloe = new PlantType("Aloe",21);
        aloe.setLightPreference(LightPreference.PART_SHADE);

        model.addAttribute("plantType",aloe);
        model.addAttribute("title","Plant Profile: " + aloe.getName());

        return "plantType/index";
    }
}

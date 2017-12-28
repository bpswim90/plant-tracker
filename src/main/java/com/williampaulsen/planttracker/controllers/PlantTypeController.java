package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.PlantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public String addPlantType(Model model) {
        model.addAttribute("title", "Add New Plant Type");
        model.addAttribute("plantType", new PlantType());
        model.addAttribute("lightPreferences",LightPreference.values());

        return "plantType/add";
    }

    //TODO: Add controller to process add form and redirect to the new plant type profile
}

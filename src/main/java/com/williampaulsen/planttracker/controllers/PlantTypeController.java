package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.PlantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(value="plant/type")
public class PlantTypeController {

    ArrayList<PlantType> plantTypes = new ArrayList<>();

    @RequestMapping(value = "")
    public String index(Model model) {
        PlantType aloe = new PlantType("Aloe",21);
        aloe.setLightPreference(LightPreference.PART_SHADE);

        model.addAttribute("plantTypes",plantTypes);
        model.addAttribute("title","Plants");

        return "plantType/index";
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addPlantType(Model model) {
        model.addAttribute("title", "Add New Plant Type");
        model.addAttribute("plantType", new PlantType());
        model.addAttribute("lightPreferences",LightPreference.values());

        return "plantType/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addPlantType(Model model, @ModelAttribute PlantType newPlantType) {
        plantTypes.add(newPlantType);

        return "redirect:";
    }

    //View for individual plant types.
    @RequestMapping(value="/{plantType_id}")
    public String viewPlantType(Model model, @PathVariable int plantType_id) {

        for (PlantType plantType : plantTypes) {
            if (plantType.getId() == plantType_id) {
                PlantType thisPlantType = plantType;
                model.addAttribute("plantType",thisPlantType);

                return "plantType/view";
            }
        }

        return "redirect:";
    }
}

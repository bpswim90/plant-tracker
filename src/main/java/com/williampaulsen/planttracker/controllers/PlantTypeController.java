package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.PlantType;
import com.williampaulsen.planttracker.models.data.PlantTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="plant/type")
public class PlantTypeController {

    @Autowired
    private PlantTypeDao plantTypeDao;

    //View displays all saved plantTypes
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("plantTypes",plantTypeDao.findAll());
        model.addAttribute("title","Plants");

        return "plantType/index";
    }

    //Displays form to add a new plantType
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addPlantType(Model model) {
        model.addAttribute("title", "Add New Plant Type");
        model.addAttribute("plantType", new PlantType());
        model.addAttribute("lightPreferences",LightPreference.values());

        return "plantType/add";
    }

    //Processes the plantType form and saves a validated plantType
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addPlantType(Model model,
                               @ModelAttribute @Valid PlantType newPlantType,
                               Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("plantType", newPlantType);
            return "plantType/add";
        }

        plantTypeDao.save(newPlantType);

        return "redirect:";
    }

    //View for individual plant types.
    @RequestMapping(value="/{plantType_id}")
    public String viewPlantType(Model model, @PathVariable int plantType_id) {

        PlantType thisPlantType = plantTypeDao.findOne(plantType_id);
        model.addAttribute("plantType", thisPlantType);

        return "plantType/view";
    }
}

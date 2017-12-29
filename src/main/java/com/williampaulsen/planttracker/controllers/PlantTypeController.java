package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.PlantType;
import com.williampaulsen.planttracker.models.data.PlantTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("title","All Plant Types");

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
            model.addAttribute("title", "Add New Plant Type");
            model.addAttribute("plantType", newPlantType);
            model.addAttribute("lightPreferences",LightPreference.values());
            return "plantType/add";
        }

        plantTypeDao.save(newPlantType);

        return "redirect:";
    }

    //View for individual plant types.
    @RequestMapping(value="/{plantType_id}")
    public String viewPlantType(Model model, @PathVariable int plantType_id) {

        PlantType thisPlantType = plantTypeDao.findOne(plantType_id);
        model.addAttribute("title","Profile for: " + thisPlantType.getName());
        model.addAttribute("plantType", thisPlantType);

        return "plantType/view";
    }

    //Display remove plant type form.
    @RequestMapping(value="/remove", method=RequestMethod.GET)
    public String removePlantType(Model model) {

        model.addAttribute("title","Remove Plant Type");
        model.addAttribute("plantTypes",plantTypeDao.findAll());

        return "plantType/remove";
    }

    //Process remove plant type form.
    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public String processRemovePlantType(@RequestParam int[] plantTypeIds) {

        for (int plantTypeId : plantTypeIds) {
            plantTypeDao.delete(plantTypeId);
        }

        return "redirect:";
    }
}

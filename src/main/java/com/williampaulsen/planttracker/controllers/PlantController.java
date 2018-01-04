package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.Plant;
import com.williampaulsen.planttracker.models.PlantType;
import com.williampaulsen.planttracker.models.data.PlantDao;
import com.williampaulsen.planttracker.models.data.PlantTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="plant")
public class PlantController {

    @Autowired
    private PlantTypeDao plantTypeDao;

    @Autowired
    private PlantDao plantDao;

    //View displays all saved plants ("My Plants.").
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("plants",plantDao.findAll());
        model.addAttribute("title","My Plants");

        return "plant/index";
    }

    //This view will ask the user to select the desired plant type or create a new type.
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String selectType(Model model) {

        model.addAttribute("title","Add New Plant");
        model.addAttribute("plantTypes",plantTypeDao.findAll());

        return "plant/selectType";
    }

    //Displays add plant form, with values of selected plant type filled in automatically.
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String displayAddPlantForm(Model model, @RequestParam int plantTypeId) {

        //Below is just temporary code.
        //TODO: Have this controller pass in a new plant object to render an Add Plant form,
        //TODO: and auto-fill relevant fields with those from its plantType.

        PlantType thisPlantType = plantTypeDao.findOne(plantTypeId);

        model.addAttribute("title","Add New Plant");
        model.addAttribute("plant",new Plant());
        //model.addAttribute("plantType",thisPlantType);
        model.addAttribute("plantTypes", plantTypeDao.findAll());
        model.addAttribute("lightPreferences",LightPreference.values());

        return "plant/add";
    }
}

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
    @RequestMapping(value="add")
    public String selectType(Model model) {

        model.addAttribute("title","Add New Plant");
        model.addAttribute("plantTypes",plantTypeDao.findAll());

        return "plant/selectType";
    }

    //TODO: Write a controller to display an add form for a new plant. 
}

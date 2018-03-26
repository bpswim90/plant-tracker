package com.williampaulsen.planttracker.controllers;

import com.williampaulsen.planttracker.models.DateComparator;
import com.williampaulsen.planttracker.models.LightPreference;
import com.williampaulsen.planttracker.models.Plant;
import com.williampaulsen.planttracker.models.PlantType;
import com.williampaulsen.planttracker.models.data.PlantDao;
import com.williampaulsen.planttracker.models.data.PlantTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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

        ArrayList<Plant> plants = new ArrayList<>();
        ArrayList<String> needWater = new ArrayList<>();

        for (Plant plant : plantDao.findAll()) {
            plants.add(plant);
        }

        //Sorts plants by which needs water soonest.
        DateComparator comparator = new DateComparator();
        plants.sort(comparator);

        //Adds plants that need water to a notification.
        for (Plant plant : plants) {
            if (plant.needsWater()) {
                needWater.add(plant.getName());
            }
        }

        model.addAttribute("needWater", needWater);
        model.addAttribute("plants",plants);
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

        PlantType thisPlantType = plantTypeDao.findOne(plantTypeId);
        Plant formPlant = new Plant();

        //Assigns the selected plantType's properties to the formPlant to be rendered in the add form.
        formPlant.setPlantType(thisPlantType);
        formPlant.setDaysBetweenWater(thisPlantType.getDaysBetweenWater());
        formPlant.setLightPreference(thisPlantType.getLightPreference());

        model.addAttribute("title","Add New Plant");
        model.addAttribute("plant",formPlant);
        model.addAttribute("plantTypes", plantTypeDao.findAll());
        model.addAttribute("lightPreferences",LightPreference.values());

        return "plant/add";
    }

    //Validates form data and saves the plant to the database if there are no errors.
    @RequestMapping(value="add/validate", method=RequestMethod.POST)
    public String processAddPlantForm(Model model,
                                      @ModelAttribute @Valid Plant newPlant,
                                      Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Add New Plant");
            model.addAttribute("plant",newPlant);
            model.addAttribute("plantTypes", plantTypeDao.findAll());
            model.addAttribute("lightPreferences",LightPreference.values());

            return "plant/add";
        }

        plantDao.save(newPlant);

        return "redirect:/plant";
    }

    //View for individual plant.
    @RequestMapping(value="/{plant_id}")
    public String viewPlant(Model model, @PathVariable int plant_id) {

        Plant thisPlant = plantDao.findOne(plant_id);
        model.addAttribute("title","Profile for: " + thisPlant.getName());
        model.addAttribute("plant", thisPlant);

        return "plant/view";
    }

    //Displays edit plant form.
    @RequestMapping(value="/edit/{plant_id}", method=RequestMethod.GET)
    public String editPlant(Model model, @PathVariable int plant_id) {
        Plant thisPlant = plantDao.findOne(plant_id);

        model.addAttribute("title","Edit Plant: " + thisPlant.getName());
        model.addAttribute("plant",thisPlant);
        model.addAttribute("plantTypes", plantTypeDao.findAll());
        model.addAttribute("lightPreferences", LightPreference.values());
        model.addAttribute("plantId",plant_id);

        return "plant/edit";
    }

    //Process edit plant form and save changes to database.
    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String processEditPlan(Model model,
                                  @RequestParam int plantId,
                                  @ModelAttribute @Valid Plant formPlant,
                                  Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Edit Plant: " + formPlant.getName());
            model.addAttribute("plant",formPlant);
            model.addAttribute("plantTypes", plantTypeDao.findAll());
            model.addAttribute("lightPreferences", LightPreference.values());
            model.addAttribute("plantId",plantId);

            return "plant/edit";
        }

        Plant thisPlant = plantDao.findOne(plantId);

        thisPlant.setName(formPlant.getName());
        thisPlant.setDaysBetweenWater(formPlant.getDaysBetweenWater());
        thisPlant.setLightPreference(formPlant.getLightPreference());
        thisPlant.setPlantType(formPlant.getPlantType());
        thisPlant.setCareInstructions(formPlant.getCareInstructions());
        plantDao.save(thisPlant);

        return "redirect:";
    }

    //Display remove plant form.
    @RequestMapping(value="/remove", method=RequestMethod.GET)
    public String removePlant(Model model) {

        model.addAttribute("title","Remove Plant");
        model.addAttribute("plants",plantDao.findAll());

        return "plant/remove";
    }

    //Process remove plant form.
    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public String processRemovePlant(@RequestParam int[] plantIds) {

        for (int plantId : plantIds) {
            plantDao.delete(plantId);
        }

        return "redirect:";
    }

    //Waters an individual plant and saves the change to the database.
    @RequestMapping(value="/water/{plant_id}")
    public String waterPlant(@PathVariable int plant_id) {
        Plant thisPlant = plantDao.findOne(plant_id);
        thisPlant.water();
        plantDao.save(thisPlant);

        return "redirect:/plant";
    }

    //Display quick-water page (water multiple plants).
    @RequestMapping(value="/water", method=RequestMethod.GET)
    public String displayQuickWater(Model model) {

        model.addAttribute("title","Water Plants");
        model.addAttribute("plants",plantDao.findAll());

        return "plant/water";
    }

    //Process quick water form.
    @RequestMapping(value="/water", method=RequestMethod.POST)
    public String processQuickWater(@RequestParam int[] plantIds) {

        for (int plantId : plantIds) {
            Plant thisPlant = plantDao.findOne(plantId);
            thisPlant.water();
            plantDao.save(thisPlant);
        }

        return "redirect:/plant";
    }
}

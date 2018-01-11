package com.williampaulsen.planttracker.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//This class encapsulates the specific care profile for an individual plant.

@Entity
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=20, message="Must be between 3 and 20 characters.")
    private String name;

    @NotNull
    @Min(value=1, message="Must be between 1 and 30.")
    @Max(value=30, message="Must be between 1 and 30.")
    private int daysBetweenWater;

    @NotNull
    private LightPreference lightPreference;

    private String lastWatered;

    private String nextWater;

    @Size(max=250, message="Must be less than 250 characters long.")
    private String careInstructions;

    @ManyToOne
    private PlantType plantType;

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Plant() {

    }

    public Plant(String name, int daysBetweenWater) {
        this.name = name;
        this.daysBetweenWater = daysBetweenWater;
    }

    public Plant(PlantType plantType, String name) {
        this.name = name;
        this.daysBetweenWater = plantType.getDaysBetweenWater();
        this.lightPreference = plantType.getLightPreference();
        this.plantType = plantType;
    }

    //Checks if the plant needs to be watered right now.
    public boolean needsWater() {
        if (this.nextWater == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        LocalDate nextWaterDate = LocalDate.parse(this.nextWater,formatter);

        if (nextWaterDate.isEqual(today) || nextWaterDate.isBefore(today)) {
            return true;
        }

        return false;
    }

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public void water() {
        LocalDate date = LocalDate.now();
        LocalDate nextWaterDate = date.plusDays(daysBetweenWater);
        String lastWatered = date.format(formatter);
        String nextWater = nextWaterDate.format(formatter);
        this.lastWatered = lastWatered;
        this.nextWater = nextWater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysBetweenWater() {
        return daysBetweenWater;
    }

    public void setDaysBetweenWater(int daysBetweenWater) {
        this.daysBetweenWater = daysBetweenWater;
    }

    public LightPreference getLightPreference() {
        return lightPreference;
    }

    public void setLightPreference(LightPreference lightPreference) {
        this.lightPreference = lightPreference;
    }

    public int getId() {
        return id;
    }

    public String getNextWater() {
        return nextWater;
    }

    public void setNextWater(String nextWater) {
        this.nextWater = nextWater;
    }

    public String getCareInstructions() {
        return careInstructions;
    }

    public void setCareInstructions(String careInstructions) {
        this.careInstructions = careInstructions;
    }
}

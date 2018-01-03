package com.williampaulsen.planttracker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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

    private LocalDate lastWatered;

    @ManyToOne
    private PlantType plantType;

    public Plant() {

    }

    public Plant(PlantType plantType, String name) {
        this.name = name;
        this.daysBetweenWater = plantType.getDaysBetweenWater();
        this.lightPreference = plantType.getLightPreference();
        this.plantType = plantType;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public void water() {
        this.lastWatered = LocalDate.now();
    }
}

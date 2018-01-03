package com.williampaulsen.planttracker.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//This class encapsulates the general care profile for a type of plant, e.g. a jade plant, or an aloe.

@Entity
public class PlantType {

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

    @OneToMany
    @JoinColumn(name = "plant_type_id")
    private List<Plant> plants = new ArrayList<>();

    public PlantType () {
    }

    public PlantType(String name, int daysBetweenWater) {
        this.name = name;
        this.daysBetweenWater = daysBetweenWater;
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
}

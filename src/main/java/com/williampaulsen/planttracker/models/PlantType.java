package com.williampaulsen.planttracker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//This class encapsulates the general care profile for a type of plant, e.g. a jade plant, or an aloe.

@Entity
public class PlantType {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=20)
    private String name;

    @NotNull
    private int daysBetweenWater;

    @NotNull
    private LightPreference lightPreference;

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

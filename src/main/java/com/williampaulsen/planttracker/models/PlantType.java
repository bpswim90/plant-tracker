package com.williampaulsen.planttracker.models;


//This class encapsulates the general care profile for a type of plant, e.g. a jade plant, or an aloe.


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlantType {

    private String name;

    private int daysBetweenWater;

    private LightPreference lightPreference;

    private final int id;

    private static int nextId = 1;

    public PlantType () {
        this.id = nextId;
        nextId++;
    }

    public PlantType(String name, int daysBetweenWater) {
        this();
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

package com.williampaulsen.planttracker.models;



public class PlantType {

    private String name;
    private int daysBetweenWater;
    private final int id;
    private static int nextId = 1;

    //TODO - create lightPreference enum

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

    public int getId() {
        return id;
    }
}

package com.williampaulsen.planttracker.models;

public enum LightPreference {

    SHADE ("Full Shade"),
    PART_SHADE ("Partial Shade"),
    PART_SUN ("Partial Sun"),
    SUN ("Full Sun");

    private final String name;

    LightPreference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

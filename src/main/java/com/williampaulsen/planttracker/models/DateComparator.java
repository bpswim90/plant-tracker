package com.williampaulsen.planttracker.models;

import java.util.Comparator;

public class DateComparator implements Comparator<Plant> {

    @Override
    public int compare(Plant o1, Plant o2) {
        return o1.getNextWaterDate().compareTo(o2.getNextWaterDate());
    }
}

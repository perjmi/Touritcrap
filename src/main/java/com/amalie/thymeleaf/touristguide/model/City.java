package com.amalie.thymeleaf.touristguide.model;

public class City {
    private String name;
    private int cityId;

    public City(String name, int cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
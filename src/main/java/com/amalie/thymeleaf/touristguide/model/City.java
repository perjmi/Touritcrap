package com.amalie.thymeleaf.touristguide.model;

public class City {
    private String name;
    private int id;

    public City(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public City (int id) {
        this.id = id;
        this.name = "Ingen steder";
    }
    public City() {
        this.id = 1;
        this.name = "Roskilde";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

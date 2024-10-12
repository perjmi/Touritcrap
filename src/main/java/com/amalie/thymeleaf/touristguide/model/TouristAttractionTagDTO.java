package com.amalie.thymeleaf.touristguide.model;

import java.util.List;

public class TouristAttractionTagDTO {
    private String name;
    private int tourist_id;
    private String description;
    private double prisDollar;
    private int cityId;
    List<Integer> tagIds = new java.util.ArrayList<>();

    public TouristAttractionTagDTO(String name, int tourist_id, String description, double prisDollar, int cityId) {
        this.name = name;
        this.tourist_id = tourist_id;
        this.description = description;
        this.prisDollar = prisDollar;
        this.cityId = cityId;
    }

    public TouristAttractionTagDTO(String name, int tourist_id, String description, double prisDollar, int cityId, List<Integer> tagIds) {
        this.name = name;
        this.tourist_id = tourist_id;
        this.description = description;
        this.prisDollar = prisDollar;
        this.cityId = cityId;
        this.tagIds = tagIds;
    }

    public TouristAttractionTagDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTourist_id() {
        return tourist_id;
    }

    public void setTourist_id(int tourist_id) {
        this.tourist_id = tourist_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrisDollar() {
        return prisDollar;
    }

    public void setPrisDollar(double prisDollar) {
        this.prisDollar = prisDollar;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }


    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "TouristAttractionTagDTO{" +
                "name='" + name + '\'' +
                ", tourist_id=" + tourist_id +
                ", description='" + description + '\'' +
                ", prisDollar=" + prisDollar +
                ", cityId=" + cityId +
                ", tagIds=" + tagIds +
                '}';
    }
}

package com.amalie.thymeleaf.touristguide.model;

//he
public class Tag {
    private String name;
    private int id;

    public Tag(String name, int id) {
        this.name = name;
        this.id = id;
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
        return "Tag{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
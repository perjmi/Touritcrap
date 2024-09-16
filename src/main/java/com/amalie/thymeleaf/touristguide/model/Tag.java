package com.amalie.thymeleaf.touristguide.model;

public enum Tag {
    FORLYSTELSE("Forlystelse"),
    BALLON("Ballon"),
    SLIK("Slik"),
    MUSEUM("Museum"),
    NATUR("Natur"),
    HISTORIE("Historie"),
    STRAND("Strand"),
    RESTAURANT("Restaurant"),
    SHOPPING("Shopping"),
    SPORT("Sport"),
    KONCERT("Koncert"),
    FESTIVAL("Festival"),
    SEVÆRDIGHED("Seværdighed"),
    PARK("Park");

    private String displayName;

    Tag(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
package com.amalie.thymeleaf.touristguide.model;
//he
public enum Tag {
    FORLYSTELSE("Forlystelse"),
    BALLON("Ballon"),
    SLIK("Slik"),
    MUSEUM("Museum"),
    NATUR("Natur"),
    HISTORIE("Historie"),
    STRAND("Strand");

    final private String displayName;

    Tag(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
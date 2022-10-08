package com.example.roombookingapp.model;

public enum Layout {
    THEATER("Theater"),
    USHAPE("U-Shape"),
    BOARD("Board Meeting");


    private String description;

    Layout(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

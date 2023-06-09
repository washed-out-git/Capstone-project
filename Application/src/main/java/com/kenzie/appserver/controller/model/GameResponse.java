package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResponse {

    @JsonProperty("title")
    private String title;

    @JsonProperty("loaner")
    private String loaner;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoaner() {
        return loaner;
    }

    public void setLoaner(String loaner) {
        this.loaner = loaner;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}

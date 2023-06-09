package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CreateGameRequest {

    @NotEmpty
    @JsonProperty("title")
    private String title;

    @NotEmpty
    @JsonProperty("loaner")
    private String loaner;

    @NotEmpty
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @NotEmpty
    @JsonProperty("borrower")
    private String borrower;

    public CreateGameRequest(String title, String loaner, boolean isAvailable) {
        this.title = title;
        this.loaner = loaner;
        this.isAvailable = true;
    }

    public CreateGameRequest(String title, String loaner, String borrower, boolean isAvailable) {
        this.title = title;
        this.loaner = loaner;
        this.borrower = borrower;
        this.isAvailable = false;
    }

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

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
}

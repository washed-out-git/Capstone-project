package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "Game")
public class GameRecord {

    private String title;

    private String loaner;

    private boolean isAvailable;

    @DynamoDBHashKey(attributeName = "Title")
    public String getTitle() {
        return title;
    }

    @DynamoDBAttribute(attributeName = "Loaner")
    public String getLoaner() {
        return loaner;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLoaner(String loaner) {
        this.loaner = loaner;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

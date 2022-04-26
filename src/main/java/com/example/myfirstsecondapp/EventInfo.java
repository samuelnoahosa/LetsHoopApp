package com.example.myfirstsecondapp;

public class EventInfo {
    // string variable for
    // storing employee name.
    private String Date;

    // string variable for storing
    // employee contact number
    private String Location;

    // string variable for storing
    // employee address.
    private String Time;

    // an empty constructor is required when using Firebase Realtime Database.
    public EventInfo() {

    }

    // created getter and setter methods for all our variables.

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}

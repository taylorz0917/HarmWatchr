package com.ufl.harmwatchr.harmwatcher;


public class Child  {
    public String firstName;
    public String lastName;
//    private int childAgeInMonths;
//    private String parentId;
//    private int avgHeartRate;
//    private double latitude;
//    private double longitude;

    public Child() {

    }
    public Child(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getChildFirstName() {
        return firstName;

    }

    public void setChildFirstName(String childFirstName) {
        this.firstName = childFirstName;

    }

    public String getChildLastName() {
        return lastName;

    }

    public void setChildLastName(String childLastName) {
        this.lastName = childLastName;

    }
//
//    public int getChildAgeInMonths() {
//        return childAgeInMonths;
//
//    }
//
//    public void setChildAgeInMonths(int childAgeInMonths) {
//        this.childAgeInMonths = childAgeInMonths;
//
//    }
//
//    public String getParentId() {
//        return parentId;
//
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//
//    }
//
//    public int getAvgHeartRate() {
//        return avgHeartRate;
//
//    }
//
//    public void setAvgHeartRate(int avgHeartRate) {
//        this.avgHeartRate = avgHeartRate;
//
//    }
//
//    public double getLatitude() {
//        return latitude;
//
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//
//    }
//
//    public double getLongitude() {
//        return longitude;
//
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//
//    }

}
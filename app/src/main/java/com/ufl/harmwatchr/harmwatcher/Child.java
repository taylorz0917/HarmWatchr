package com.ufl.harmwatchr.harmwatcher;

import android.os.Parcel;
import android.os.Parcelable;

public class Child implements Parcelable {
    public String CREATOR;
    public String firstName;
    public String lastName;
    public String ageInMonths;
    private String parentId;
    private int avgHeartRate;
    private double latitude;
    private double longitude;

    public Child() {

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

    public String getChildAgeInMonths() {
            return ageInMonths;

    }

    public void setChildAgeInMonths(String childAgeInMonths) {
            this.ageInMonths = childAgeInMonths;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public String getParentId() {
        return parentId;

    }

    public void setParentId(String parentId) {
        this.parentId = parentId;

    }

    public int getAvgHeartRate() {
        return avgHeartRate;

    }

    public void setAvgHeartRate(int avgHeartRate) {
        this.avgHeartRate = avgHeartRate;

    }

    public double getLatitude() {
        return latitude;

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;

    }

    public double getLongitude() {
        return longitude;

    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;

    }

}
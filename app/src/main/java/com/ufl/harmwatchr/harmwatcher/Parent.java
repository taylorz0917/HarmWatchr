package com.ufl.harmwatchr.harmwatcher;

import android.os.Parcel;
import android.os.Parcelable;

public class Parent implements Parcelable {
    static String CREATOR;

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private int numChildren;
    private String emergencyContactFirstName;
    private String emergencyContactLastName;
    private String emergencyContactPhone;
    private String child1ID;
    private String child2ID;
    private String child3ID;

    public Parent() {

    }
    /*
    public Parent(long phone, String firstName, String lastName, int numChildren, String child1ID, String child2ID, String child3ID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.numChildren = numChildren;
        this.child1ID = child1ID;
        this.child2ID = child2ID;
        this.child3ID = child3ID;
    }*/

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public String getLastName() {
        return lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren){
        this.numChildren = numChildren;
    }


    public String getEmergencyContactFirstName(){
        return emergencyContactFirstName;
    }

    public void setEmergencyContactFirstName(String emergencyContactFirstName){
        this.emergencyContactFirstName = emergencyContactFirstName;
    }

    public String getEmergencyContactLastName(){
        return emergencyContactLastName;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName){
        this.emergencyContactLastName = emergencyContactLastName;
    }

    public String getEmergencyContactPhone(){
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getChild1ID() {
        return child1ID;
    }

    public void setChild1ID(String child1ID){
        this.child1ID = child1ID;
    }

    public String getChild2ID() {
        return child2ID;
    }

    public void setChild2ID(String child2ID){
        this.child2ID = child2ID;
    }

    public String getChild3ID() {
        return child3ID;
    }

    public void setChild3ID(String child3ID){
        this.child3ID = child3ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

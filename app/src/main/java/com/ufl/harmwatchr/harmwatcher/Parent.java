package com.ufl.harmwatchr.harmwatcher;

/**
 * Created by Zak Taylor on 4/12/2017.
 */

public class Parent {
    private String email;
    private String firstName;
    private String lastName;
    private long phone;
    private int numChildren;
    private String emergencyContactFirstName;
    private String emergencyContactLastName;
    private long emergencyContactPhone;
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

    public long getPhone(){
        return phone;
    }

    public void setPhone(long phone){
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

    public long getEmergencyContactPhone(){
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(long emergencyContactPhone) {
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

}

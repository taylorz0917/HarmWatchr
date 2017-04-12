package com.ufl.harmwatchr.harmwatcher;

/**
 * Created by Zak Taylor on 4/12/2017.
 */

public class Parent {
//    private String email;
    private String firstName;
    private String lastName;
//    private int phone;
    private int numChildren;
//    private String ecFirst;
//    private String ecLast;
//    private int ecPhone;
    private String child1ID;
    private String child2ID;
    private String child3ID;

    public Parent() {

    }
    public Parent(String firstName, String lastName, int numChildren, String child1ID, String child2ID, String child3ID){
        this.firstName = firstName;
        this.lastName = lastName;
        //this.phone = phone;
        this.numChildren = numChildren;
        this.child1ID = child1ID;
        this.child2ID = child2ID;
        this.child3ID = child3ID;
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

//    public int getPhone(){
//        return phone;
//    }
//
//    public void setPhone(int phone){
//        this.phone = phone;
//    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren){
        this.numChildren = numChildren;
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

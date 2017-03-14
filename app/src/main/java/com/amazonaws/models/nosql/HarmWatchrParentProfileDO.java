package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "harmwatchr-mobilehub-839795529-HarmWatchr_ParentProfile")

public class HarmWatchrParentProfileDO {
    private String _userId;
    private Double _child1AgeInMonths;
    private String _child1Name;
    private String _emergencyContactName;
    private Double _emergencyContactPhoneNumber;
    private String _userCar1Color;
    private String _userCar1LicensePlate;
    private String _userCar1Make;
    private String _userCar1Model;
    private Double _userCar1Year;
    private String _userName;
    private Double _userPhoneNumber;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "child1AgeInMonths")
    public Double getChild1AgeInMonths() {
        return _child1AgeInMonths;
    }

    public void setChild1AgeInMonths(final Double _child1AgeInMonths) {
        this._child1AgeInMonths = _child1AgeInMonths;
    }
    @DynamoDBAttribute(attributeName = "child1Name")
    public String getChild1Name() {
        return _child1Name;
    }

    public void setChild1Name(final String _child1Name) {
        this._child1Name = _child1Name;
    }
    @DynamoDBAttribute(attributeName = "emergencyContactName")
    public String getEmergencyContactName() {
        return _emergencyContactName;
    }

    public void setEmergencyContactName(final String _emergencyContactName) {
        this._emergencyContactName = _emergencyContactName;
    }
    @DynamoDBAttribute(attributeName = "emergencyContactPhoneNumber")
    public Double getEmergencyContactPhoneNumber() {
        return _emergencyContactPhoneNumber;
    }

    public void setEmergencyContactPhoneNumber(final Double _emergencyContactPhoneNumber) {
        this._emergencyContactPhoneNumber = _emergencyContactPhoneNumber;
    }
    @DynamoDBAttribute(attributeName = "userCar1Color")
    public String getUserCar1Color() {
        return _userCar1Color;
    }

    public void setUserCar1Color(final String _userCar1Color) {
        this._userCar1Color = _userCar1Color;
    }
    @DynamoDBAttribute(attributeName = "userCar1LicensePlate")
    public String getUserCar1LicensePlate() {
        return _userCar1LicensePlate;
    }

    public void setUserCar1LicensePlate(final String _userCar1LicensePlate) {
        this._userCar1LicensePlate = _userCar1LicensePlate;
    }
    @DynamoDBAttribute(attributeName = "userCar1Make")
    public String getUserCar1Make() {
        return _userCar1Make;
    }

    public void setUserCar1Make(final String _userCar1Make) {
        this._userCar1Make = _userCar1Make;
    }
    @DynamoDBAttribute(attributeName = "userCar1Model")
    public String getUserCar1Model() {
        return _userCar1Model;
    }

    public void setUserCar1Model(final String _userCar1Model) {
        this._userCar1Model = _userCar1Model;
    }
    @DynamoDBAttribute(attributeName = "userCar1Year")
    public Double getUserCar1Year() {
        return _userCar1Year;
    }

    public void setUserCar1Year(final Double _userCar1Year) {
        this._userCar1Year = _userCar1Year;
    }
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }
    @DynamoDBAttribute(attributeName = "userPhoneNumber")
    public Double getUserPhoneNumber() {
        return _userPhoneNumber;
    }

    public void setUserPhoneNumber(final Double _userPhoneNumber) {
        this._userPhoneNumber = _userPhoneNumber;
    }

}

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

@DynamoDBTable(tableName = "harmwatchr-mobilehub-839795529-HarmWatchr_ChildProfile")

public class HarmWatchrChildProfileDO {
    private String _userId;
    private String _parentUserId;
    private Double _childAgeInMonths;
    private Double _childGPSXLocation;
    private Double _childGPSYLocation;
    private Double _childHeartRate;
    private Boolean _childInDanger;
    private String _childName;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "parentUserId")
    @DynamoDBAttribute(attributeName = "parentUserId")
    public String getParentUserId() {
        return _parentUserId;
    }

    public void setParentUserId(final String _parentUserId) {
        this._parentUserId = _parentUserId;
    }
    @DynamoDBAttribute(attributeName = "childAgeInMonths")
    public Double getChildAgeInMonths() {
        return _childAgeInMonths;
    }

    public void setChildAgeInMonths(final Double _childAgeInMonths) {
        this._childAgeInMonths = _childAgeInMonths;
    }
    @DynamoDBAttribute(attributeName = "childGPS_X_location")
    public Double getChildGPSXLocation() {
        return _childGPSXLocation;
    }

    public void setChildGPSXLocation(final Double _childGPSXLocation) {
        this._childGPSXLocation = _childGPSXLocation;
    }
    @DynamoDBAttribute(attributeName = "childGPS_Y_location")
    public Double getChildGPSYLocation() {
        return _childGPSYLocation;
    }

    public void setChildGPSYLocation(final Double _childGPSYLocation) {
        this._childGPSYLocation = _childGPSYLocation;
    }
    @DynamoDBAttribute(attributeName = "childHeartRate")
    public Double getChildHeartRate() {
        return _childHeartRate;
    }

    public void setChildHeartRate(final Double _childHeartRate) {
        this._childHeartRate = _childHeartRate;
    }
    @DynamoDBAttribute(attributeName = "childInDanger")
    public Boolean getChildInDanger() {
        return _childInDanger;
    }

    public void setChildInDanger(final Boolean _childInDanger) {
        this._childInDanger = _childInDanger;
    }
    @DynamoDBAttribute(attributeName = "childName")
    public String getChildName() {
        return _childName;
    }

    public void setChildName(final String _childName) {
        this._childName = _childName;
    }

}

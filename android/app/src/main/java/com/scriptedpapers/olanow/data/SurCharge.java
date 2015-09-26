package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mahes on 25/9/15.
 */
public class SurCharge {

    @SerializedName("name")
    @Expose
    private String rideName;

    @SerializedName("type")
    @Expose
    private String rideType;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("value")
    @Expose
    private String rideCostValue;

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRideCostValue() {
        return rideCostValue;
    }

    public void setRideCostValue(String rideCostValue) {
        this.rideCostValue = rideCostValue;
    }
}

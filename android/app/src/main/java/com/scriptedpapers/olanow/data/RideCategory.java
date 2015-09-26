package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mahes on 25/9/15.
 */
public class RideCategory {

    @SerializedName("id")
    @Expose
    private String rideId;

    @SerializedName("display_name")
    @Expose
    private String displayName;

    @SerializedName("currency")
    @Expose
    private String currencyType;

    @SerializedName("distance_unit")
    @Expose
    private String distanceUnit;

    @SerializedName("time_unit")
    @Expose
    private String timeUnit;

    @SerializedName("eta")
    @Expose
    private String estimatedATime;

    @SerializedName("distance")
    @Expose
    private String distance;

    @SerializedName("image")
    @Expose
    private String image;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getEstimatedATime() {
        return estimatedATime;
    }

    public void setEstimatedATime(String estimatedATime) {
        this.estimatedATime = estimatedATime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mahes on 25/9/15.
 */
public class FareBreakUp {

    @SerializedName("type")
    @Expose
    private String rideType;

    @SerializedName("minimum_distance")
    @Expose
    private  String minimumDistance;

    @SerializedName("minimum_time")
    @Expose
    private  String minimumTime;

    @SerializedName("base_fare")
    @Expose
    private String baseFare;

    @SerializedName("cost_per_distance")
    @Expose
    private  String costPerDistance;

    @SerializedName("waiting_cost_per_minute")
    @Expose
    private String waitingCostPerDistance;

    @SerializedName("ride_cost_per_minute")
    @Expose
    private String rideCostPerDistance;

    @SerializedName("fare_breakup")
    @Expose
    private List<FareBreakUp> fareBreakUp;

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public String getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(String minimumDistance) {
        this.minimumDistance = minimumDistance;
    }

    public String getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(String minimumTime) {
        this.minimumTime = minimumTime;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getCostPerDistance() {
        return costPerDistance;
    }

    public void setCostPerDistance(String costPerDistance) {
        this.costPerDistance = costPerDistance;
    }

    public String getWaitingCostPerDistance() {
        return waitingCostPerDistance;
    }

    public void setWaitingCostPerDistance(String waitingCostPerDistance) {
        this.waitingCostPerDistance = waitingCostPerDistance;
    }

    public String getRideCostPerDistance() {
        return rideCostPerDistance;
    }

    public void setRideCostPerDistance(String rideCostPerDistance) {
        this.rideCostPerDistance = rideCostPerDistance;
    }
}

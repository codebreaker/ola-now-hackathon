package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jagadeeshwarank on 26/9/15.
 */
public class RideEstimate {
    /*
     "category" :  "sedan" ,
            "distance" :  7.272 ,
            "travel_time_in_minutes" :  37 ,
            "amount_min" :  143 ,
            "amount_max" :  164
     */

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("distance")
    @Expose
    private double distance;

    @SerializedName("travel_time_in_minutes")
    @Expose
    private long travelTimeMins;

    @SerializedName("amount_min")
    @Expose
    private long minAmount;

    @SerializedName("amount_max")
    @Expose
    private long maxAmount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getTravelTimeMins() {
        return travelTimeMins;
    }

    public void setTravelTimeMins(long travelTimeMins) {
        this.travelTimeMins = travelTimeMins;
    }

    public long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(long minAmount) {
        this.minAmount = minAmount;
    }

    public long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(long maxAmount) {
        this.maxAmount = maxAmount;
    }
}

package com.scriptedpapers.olanow.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scriptedpapers.olanow.data.RideCategory;
import com.scriptedpapers.olanow.data.RideEstimate;

import java.util.List;

/**
 * Created by jagadeeshwarank on 26/9/15.
 */
public class EstimateRideResponse {
    @SerializedName("categories")
    @Expose
    private List<RideCategory> rideCategoryList;

    @SerializedName("ride_estimate")
    @Expose
    private List<RideEstimate> rideEstimateList;

    public List<RideCategory> getRideCategoryList() {
        return rideCategoryList;
    }

    public void setRideCategoryList(List<RideCategory> rideCategoryList) {
        this.rideCategoryList = rideCategoryList;
    }

    public List<RideEstimate> getRideEstimateList() {
        return rideEstimateList;
    }

    public void setRideEstimateList(List<RideEstimate> rideEstimateList) {
        this.rideEstimateList = rideEstimateList;
    }
}

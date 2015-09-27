package com.scriptedpapers.olanow.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scriptedpapers.olanow.data.RideCategory;
import com.scriptedpapers.olanow.data.RideEstimate;

import java.util.List;

/**
 * Created by jagadeeshwarank on 27/9/15.
 */
public class RideAvailabilityResponse {
    @SerializedName("categories")
    @Expose
    private List<RideCategory> rideCategoryList;

    public List<RideCategory> getRideCategoryList() {
        return rideCategoryList;
    }

    public void setRideCategoryList(List<RideCategory> rideCategoryList) {
        this.rideCategoryList = rideCategoryList;
    }
}

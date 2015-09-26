package com.scriptedpapers.olanow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jagadeeshwarank on 26/9/15.
 */
public class BookedRide {
    /*
    {
  "crn": "1043",
  "driver_name": "Phonenix D164",
  "driver_number": "4567894164",
  "cab_type": "sedan",
  "cab_number": "KA 16  4",
  "car_model": "Toyota Corolla",
  "eta": 2,
  "driver_lat": 12.950074,
  "driver_lng": 77.641727
}
     */

    @SerializedName("crn")
    @Expose
    private String crn;

    @SerializedName("driver_name")
    @Expose
    private String driverName;

    @SerializedName("driver_number")
    @Expose
    private String driverNumber;

    @SerializedName("cab_type")
    @Expose
    private String cabType;

    @SerializedName("cab_number")
    @Expose
    private String cabNumber;

    @SerializedName("car_model")
    @Expose
    private String carModel;

    @SerializedName("eta")
    @Expose
    private long eta;

    @SerializedName("driver_lat")
    @Expose
    private double driverLat;

    @SerializedName("driver_lng")
    @Expose
    private String driverLng;


    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public String getCabNumber() {
        return cabNumber;
    }

    public void setCabNumber(String cabNumber) {
        this.cabNumber = cabNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public long getEta() {
        return eta;
    }

    public void setEta(long eta) {
        this.eta = eta;
    }

    public double getDriverLat() {
        return driverLat;
    }

    public void setDriverLat(double driverLat) {
        this.driverLat = driverLat;
    }

    public String getDriverLng() {
        return driverLng;
    }

    public void setDriverLng(String driverLng) {
        this.driverLng = driverLng;
    }
}

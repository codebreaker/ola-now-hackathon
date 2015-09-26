package com.scriptedpapers.olanow.http;

import com.google.gson.JsonObject;
import com.scriptedpapers.olanow.data.BookedRide;
import com.scriptedpapers.olanow.data.response.CheckRideResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by jagadeeshwarank on 26/9/15.
 */
public interface OlaService {
    public static final String BASE_URL = "http://sandbox-t.olacabs.com";

    @GET("/v1/products")
    void getRideAvailability(@Header("X-APP-Token") String token, @Query("pickup_lat")double pickupLat, @Query("pickup_lng")double pickupLng,
                                    @Query("category") String category, Callback<CheckRideResponse> callback);

    @GET("/v1/products")
    void getRideEstimate(@Header("X-APP-Token") String token, @Query("pickup_lat")double pickupLat, @Query("pickup_lng")double pickupLng,
                                @Query("drop_lat")double dropLat, @Query("drop_lng")double dropLng, @Query("category") String category,
                                Callback<CheckRideResponse> callback);

    @GET("/v1/bookings/create")
    void bookRide(@Header("X-APP-Token") String token, @Header("AUthorization") String authorization, @Query("pickup_lat")double pickupLat,
                         @Query("pickup_lng")double pickupLng, @Query("pickup_mode")String mode, @Query("category") String category,
                         Callback<BookedRide> callback);
}

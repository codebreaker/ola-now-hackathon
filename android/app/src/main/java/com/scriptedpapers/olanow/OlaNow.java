package com.scriptedpapers.olanow;

import android.app.Application;

import com.scriptedpapers.olanow.http.OlaService;

import retrofit.RestAdapter;

/**
 * Created by jagadeeshwarank on 27/9/15.
 */
public class OlaNow extends Application {
    private static OlaService service;

    private static OlaNow app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        RestAdapter.Builder builder  = new RestAdapter.Builder()
                .setEndpoint(OlaService.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter adapter = builder.build();
        service = adapter.create(OlaService.class);
    }

    public static OlaService getOlaService(){
        return service;
    }

    public static OlaNow getInstace(){
        return app;
    }
}

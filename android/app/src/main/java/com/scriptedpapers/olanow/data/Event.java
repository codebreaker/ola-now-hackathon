package com.scriptedpapers.olanow.data;

import java.util.Date;

/**
 * Created by mahes on 26/9/15.
 */
public class Event {

    String eventName;
    Date eventDate;
    String eventLocation;
    String registeredEmailId;

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getRegisteredEmailId() {
        return registeredEmailId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setRegisteredEmailId(String registeredEmailId) {
        this.registeredEmailId = registeredEmailId;
    }
}

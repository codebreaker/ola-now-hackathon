package com.scriptedpapers.olanow.data;

/**
 * Created by mahes on 26/9/15.
 */
public class SuggestionItem {

    boolean isToday;
    boolean isTomorrow;

    boolean isMessageTrainTicket;

    Event event;

    public SuggestionItem() {

        isToday = false;
        isTomorrow = false;
        event = null;
     }

    public void setToday() {
        isToday = true;
        isTomorrow = false;
    }

    public void setTomorrow() {
        isToday = true;
        isTomorrow = false;
    }

    public void setIsMessageTrainTicket(boolean isMessageTrainTicket) {
        this.isMessageTrainTicket = isMessageTrainTicket;

        isToday = false;
        isTomorrow = false;

        event = null;
    }

    public boolean isToday() {
        return isToday;
    }

    public boolean isTomorrow() {
        return isTomorrow;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public boolean isMessageTrainTicket() {
        return isMessageTrainTicket;
    }
}

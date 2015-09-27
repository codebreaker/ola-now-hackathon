package com.scriptedpapers.olanow.data;

import java.util.Date;

/**
 * Created by mahes on 27/9/15.
 */
public class Reminder {

    String reminderId;
    String reminderName;
    long reminderDate;

    public String getReminderId() {
        return reminderId;
    }

    public String getReminderName() {
        return reminderName;
    }


    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate.getTime();
    }

    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }


    public long getReminderDate() {
        return reminderDate;
    }
}
